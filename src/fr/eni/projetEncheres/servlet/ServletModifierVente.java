package fr.eni.projetEncheres.servlet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.bean.Retrait;
import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.bll.ArticleVenduManager;
import fr.eni.projetEncheres.bll.BLLException;
import fr.eni.projetEncheres.bll.CategorieManager;
import fr.eni.projetEncheres.bll.EnchereManager;
import fr.eni.projetEncheres.bll.RetraitManager;

/**
 * Servlet implementation class ServletModifierVente
 */
@WebServlet("/ModifierVente")
public class ServletModifierVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RetraitManager retraitManager;
	private ArticleVenduManager articleVenduManager;
	private CategorieManager categorieManager;
	
	private static final String UPLOAD_DIR = "imageArticle";
	
	LocalDateTime date_debut_enchere = null;
	LocalDateTime date_fin_enchere = null;
	
	String sarticle = null; String sdecscription = null; String scategorie = null;
	String sphoto = null; String fileName = null;
	String sprix = null;
	String srue = null; String scode_postal = null; String sville = null;
	String sdate_debut = null; String sheure_debut = null; String sdate_fin = null; String sheure_fin = null;
	int intsprix = 0; int intscode_postal = 0; int intscategorie = 0;
       
	public void init() throws ServletException {
		articleVenduManager = ArticleVenduManager.getInstance();
		retraitManager = RetraitManager.getInstance();
		categorieManager = CategorieManager.getInstance();
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response));
		
		int idNo_article = 0;
		ArticleVendu articleVendu = null;
		Retrait retrait = null;
		
		List<Categorie> listCategorie = new ArrayList<>();
		
		if (!request.getParameter("idNo_article").isEmpty()) {
			idNo_article = Integer.parseInt(request.getParameter("idNo_article"));
		}
		
		try {
			articleVendu = articleVenduManager.selectArticleById(idNo_article);
			retrait = retraitManager.selectById(articleVendu.getNo_retrait());
			request.setAttribute("articleVendu", articleVendu);
			request.setAttribute("retrait", retrait);
			getDate(request, response, articleVendu.getDate_debut_encheres(), articleVendu.getDate_fin_encheres());
			request.setAttribute("sdate_debut", sdate_debut);
			request.setAttribute("sdate_fin", sdate_fin);
			request.setAttribute("sheure_debut", sheure_debut);
			request.setAttribute("sheure_fin", sheure_fin);
		} catch (BLLException e1) {
			e1.printStackTrace();
		}

		try {
			listCategorie = categorieManager.selectall();
		} catch (BLLException e) {
			e.printStackTrace(); 
		}
		
		request.setAttribute("categories", listCategorie);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifierVente.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 
		
		List<String> listError = new ArrayList<>();
		
		if (request.getParameter(sarticle) == null || request.getParameter(sarticle).isEmpty()) {
			doGet(request, response);
		}
		
		getParameterAndSetAttribute(request, response, listError);
			
		// TODO If listError not empty --> dispatch à la jsp

			// --> Telecharger photo dans dossier imageArticle
			// construit le chemin du répertoire pour enregistrer le fichier téléchargé
			String uploadFilePath = request.getServletContext().getRealPath("") + "public" + File.separator + UPLOAD_DIR;
			
			// crée le répertoire de sauvegarde s'il n'existe pas
	        File fileSaveDir = new File(uploadFilePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdirs();
	        }
	        
	        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath()); 
			System.out.println(uploadFilePath);
	
			// Récupère toutes les parties de la requête et les écrit dans le fichier sur le serveur
			Part part = request.getPart("sphoto");
			fileName = getFileName(part);
			
			if(fileName != null && !fileName.isEmpty()) {
				part.write(uploadFilePath + File.separator + fileName);		
			}
		
		if(!request.getParameter("sarticle").isEmpty() && !request.getParameter("sdecscription").isEmpty() && 
				!request.getParameter("scategorie").isEmpty() && !request.getParameter("sprix").isEmpty() && 
				!request.getParameter("srue").isEmpty() && !request.getParameter("scode_postal").isEmpty() &&
				!request.getParameter("sville").isEmpty() && !request.getParameter("sdate_debut").isEmpty() &&
				!request.getParameter("sheure_debut").isEmpty() && !request.getParameter("sdate_fin").isEmpty() &&
				!request.getParameter("sheure_fin").isEmpty()
		) {
			
			System.out.println(request.getParameter("sarticle"));
			System.out.println(request.getParameter("sdecscription"));
			System.out.println(request.getParameter("scategorie"));
			System.out.println(request.getParameter("sprix"));
			System.out.println(request.getParameter("srue"));
			System.out.println(request.getParameter("scode_postal"));
			System.out.println(request.getParameter("sville"));
			System.out.println(request.getParameter("sdate_debut"));
			System.out.println(request.getParameter("sheure_debut"));
			System.out.println(request.getParameter("sdate_fin"));
			System.out.println(request.getParameter("sheure_fin"));
			
			// verifier format date envoyer et en fonction envoyer une erreur si 9:00 au lieu de 09:00
			// heure "hh:mm" : 5 car, 2 chiffres : 2 chiffres , pas sup à 23
			// date "yyyy/mm/dd"
			
		    if (request.getParameter("sheure_debut").length() != 5 
		    		&& request.getParameter("sheure_fin").length() != 5) {
				listError.add("L'heure doit être au format '00:00'");
			}
		    
			if (request.getParameter("sdate_debut").length() != 10 
					&& request.getParameter("sdate_debut").length() != 10) {
				listError.add("La date doit être au format 'AAAA/MM/JJ'");
			}

			
			//Recuperer les dates 
			date_debut_enchere = parseStringToLocalDate(request, response, request.getParameter("sdate_debut"), request.getParameter("sheure_debut"));
			date_fin_enchere = parseStringToLocalDate(request, response, request.getParameter("sdate_fin"), request.getParameter("sheure_fin"));
			
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");

			Retrait retrait = new Retrait(srue, intscode_postal, sville);

			try {
				retraitManager.insertRetrait(retrait);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		
			ArticleVendu articleVendu = null;
			if(fileName == null || fileName.isEmpty()) {
				articleVendu = new ArticleVendu(sarticle, sdecscription, date_debut_enchere, date_fin_enchere, 
						intsprix, user.getNo_utlisateur(), intscategorie, retrait.getNo_retrait()); 
			} else {
				articleVendu = new ArticleVendu(sarticle, sdecscription, date_debut_enchere, date_fin_enchere, 
						intsprix, fileName, user.getNo_utlisateur(), intscategorie, retrait.getNo_retrait()); 
			}
			
			try {
				articleVenduManager.updateArticleVendu(articleVendu);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			
			listError.addAll(articleVenduManager.getListError());
			
			if(listError.isEmpty()) {
				this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
			}
			
		}
		
		
		request.setAttribute("listError", listError);
			
		this.getServletContext().getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
	}
	
	
	

	/**
	 * @author : ws
	 * Parse String - LocalDate
	 */
	protected LocalDateTime parseStringToLocalDate(HttpServletRequest request, HttpServletResponse response,
			String dateString, String timeString) {
		LocalDateTime date = null;
		LocalDate localDate = null;
		LocalTime localTime = null;
		
		localDate = LocalDate.of(
				Integer.parseInt(dateString.substring(0,4)),
				Integer.parseInt(dateString.substring(5,7)),
				Integer.parseInt(dateString.substring(8,10))
				);
		
		localTime = LocalTime.of(
				Integer.parseInt(timeString.substring(0,2)), 
				Integer.parseInt(timeString.substring(3,5))
				);
		
		date = LocalDateTime.of(localDate, localTime);
		
		return date;
	}
	
	
	/**
	 * @author : ws
	 * recuperer les parametre et transmetre les attributs 
	 */
	protected void getParameterAndSetAttribute(HttpServletRequest request, HttpServletResponse response, List<String> listError) {
		
		if(!request.getParameter("sarticle").isEmpty()) {
			sarticle = request.getParameter("sarticle");
			request.setAttribute("sarticle", sarticle);
		}
		if(!request.getParameter("sdecscription").isEmpty()) {
			sdecscription = request.getParameter("sdecscription");
			request.setAttribute("sdecscription", sdecscription);
		}
		if(!request.getParameter("scategorie").isEmpty()) {
			scategorie = request.getParameter("scategorie");
			try {
			intscategorie = Integer.parseInt(scategorie);
		} catch(Exception e){
			listError.add("Erreur catégorie");
		}
			request.setAttribute("scategorie", scategorie);
		}
		if(!request.getParameter("sprix").isEmpty()) {
			sprix = request.getParameter("sprix");
			try {
				intsprix = Integer.parseInt(sprix);
			} catch(Exception e){
				listError.add("Le prix saisi est incorrect");
			}
			request.setAttribute("sprix", sprix);
		}
		if(!request.getParameter("srue").isEmpty()) {
			srue = request.getParameter("srue");
			request.setAttribute("srue", srue);
		}
		if(!request.getParameter("scode_postal").isEmpty()) {
			scode_postal = request.getParameter("scode_postal");
			try {
				intscode_postal = Integer.parseInt(scode_postal);
			} catch(Exception e){
				listError.add("Le Code postal saisi est incorrect");
			}
			request.setAttribute("scode_postal", scode_postal);
		}
		if(!request.getParameter("sville").isEmpty()) {
			sville = request.getParameter("sville");
			request.setAttribute("sville", sville);
		}
		if(!request.getParameter("sdate_debut").isEmpty()) {
			sdate_debut = request.getParameter("sdate_debut");
			request.setAttribute("sdate_debut", sdate_debut);
		}
		if(!request.getParameter("sheure_debut").isEmpty()) {
			sheure_debut = request.getParameter("sheure_debut");
			request.setAttribute("sheure_debut", sheure_debut);
		}
		if(!request.getParameter("sdate_fin").isEmpty()) {
			sdate_fin = request.getParameter("sdate_fin");
			request.setAttribute("sdate_fin", sdate_fin);
		}
		if(!request.getParameter("sheure_fin").isEmpty()) {
			sheure_fin = request.getParameter("sheure_fin");
			request.setAttribute("sheure_fin", sheure_fin);
		}
	}
	
	
	 /**
		 * source : journaldev.com - servlet 3 file - upload - multipartconfig-part 
	     * Méthode utilitaire pour obtenir le nom de fichier à partir de la disposition du contenu de l'en-tête HTTP
	     */
	    private String getFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
//	      System.out.println("content-disposition header= "+contentDisp);
	        String[] tokens = contentDisp.split(";");
	        for (String token : tokens) {
	            if (token.trim().startsWith("filename")) {
	                return token.substring(token.indexOf("=") + 2, token.length()-1);
	            }
	        }
	        return "";
	    }
	
	
	/**
	 * @author : ws
	 * Recuperer le nom de la page sur un format classique
	 */
	protected String getPageName(HttpServletRequest request, HttpServletResponse response) {
		String pageName = request.getServletPath().replaceAll("/.", String.valueOf(request.getServletPath().charAt(1)).toUpperCase());
		boolean check = false;
		
		while(!check) {
			for(int i=1; i<= pageName.length()-1; i++) {
				if( (pageName.charAt(i-1) >= 'a' && pageName.charAt(i-1) <= 'z') && (pageName.charAt(i) >= 'A' && pageName.charAt(i) <= 'Z') ) {
					pageName = pageName.substring(0, i) + " " + pageName.substring(i, pageName.length());
				break;
				}
				if(i == pageName.length()-1) {
					check =true;
				}
			}
		}
		
		return pageName;
	}
	
	
	protected void getDate(HttpServletRequest request, HttpServletResponse response, 
			LocalDateTime dateDebut, LocalDateTime dateFin) {
		sdate_debut = dateDebut.toLocalDate().toString();
		sdate_fin = dateFin.toLocalDate().toString();
		sheure_debut = dateDebut.toLocalTime().toString();
		sheure_fin = dateFin.toLocalTime().toString();
		
	}
	
}
