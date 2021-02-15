package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Retrait;
import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.bll.ArticleVenduManager;
import fr.eni.projetEncheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletVendreUnArticle
 */
@WebServlet("/VendreArticle")
public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private RetraitManager retraitManager;
	private ArticleVenduManager articleVenduManager;
	//private UtilisateurManager utilisateurManager;
       
	
	public void init() throws ServletException {
		//utilisateurManager = UtilisateurManager.getInstance();
		articleVenduManager = ArticleVenduManager.getInstance();
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 
		
		String sdate_debut = null; String sheure_debut = null; String sdate_fin = null; String sheure_fin = null;
		
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");
		
		if(user != null && !user.getPseudo().isEmpty()) {
			setAutomaticDate(request, response, sdate_debut, sheure_debut, sdate_fin, sheure_fin);
			this.getServletContext().getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
		}
			
		this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 
		
		List<String> listError = new ArrayList<>();
		
		LocalDateTime date_debut_enchere = null;
		LocalDateTime date_fin_enchere = null;
		
		String sarticle = null; String sdecscription = null; String scategorie = null;
		String sphoto = null; // TODO
		String sprix = null;
		String srue = null; String scode_postal = null; String sville = null;
		String sdate_debut = null; String sheure_debut = null; String sdate_fin = null; String sheure_fin = null;
		
		getParameterAndSetAttribute(request, response, listError, sarticle, sdecscription, scategorie, sprix, srue, scode_postal, sville, sdate_debut, sheure_debut, sdate_fin, sheure_fin);
			
		// If listError not empty --> dispatch à la jsp
		
		if(!request.getParameter("sarticle").isEmpty() && !request.getParameter("sdecscription").isEmpty() && 
				!request.getParameter("scategorie").isEmpty() && !request.getParameter("sprix").isEmpty() && 
				!request.getParameter("srue").isEmpty() && !request.getParameter("scode_postal").isEmpty() &&
				!request.getParameter("sville").isEmpty() && !request.getParameter("sdate_debut").isEmpty() &&
				!request.getParameter("sheure_debut").isEmpty() && !request.getParameter("sdate_fin").isEmpty() &&
				!request.getParameter("sheure_fin").isEmpty()
		) {
			
			//Recuperer les dates 
			date_debut_enchere = parseStringToLocalDate(request, response, request.getParameter("sdate_debut"), request.getParameter("sheure_debut"));
			date_fin_enchere = parseStringToLocalDate(request, response, request.getParameter("sdate_fin"), request.getParameter("sheure_fin"));

			
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");
			Retrait retrait = new Retrait(srue, Integer.parseInt(scode_postal), sville); // <== Bean retrait à complèter manque un attribut
			
			// Inserer le retrait si il n'existe pas et recuperer l'id;
			
			ArticleVendu articleVendu = new ArticleVendu(sarticle, scategorie, date_debut_enchere, date_fin_enchere, 
					Integer.parseInt(sprix), "null", user.getNo_utlisateur(), Integer.parseInt(scategorie), 666); 
			
			// Inserer l'articleVendu;
			
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
			
			
		}
		
		
		request.setAttribute("listError", listError);
			
		this.getServletContext().getRequestDispatcher("/WEB-INF/vendreUnArticle.jsp").forward(request, response);
	}
	
	/**
	 * @author : ws
	 * recuperer les parametre et transmetre les attributs 
	 */
	protected void getParameterAndSetAttribute(HttpServletRequest request, HttpServletResponse response, List<String> listError,
			String sarticle, String sdecscription, String scategorie, String sprix, String srue, String scode_postal, String sville,
			String sdate_debut, String sheure_debut, String sdate_fin, String sheure_fin) {
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
			request.setAttribute("scategorie", scategorie);
		}
		//TODO Photo?
		if(!request.getParameter("sprix").isEmpty()) {
			sprix = request.getParameter("sprix");
			try {
				Integer.parseInt(sprix);
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
				Integer.parseInt(scode_postal);
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
	 * @author : ws
	 * Parse String - LocalDate
	 */
	protected void setAutomaticDate(HttpServletRequest request, HttpServletResponse response,
			String sdate_debut, String sheure_debut, String sdate_fin, String sheure_fin) {
		LocalDate date_debut = LocalDate.now();
		LocalDate date_fin = LocalDate.now().plusDays(7);
		LocalTime heure = LocalTime.now().plusHours(1);
		String heureString = heure.toString().substring(0,2) + ":" + heure.toString().substring(3,5);
		
		sdate_debut = date_debut.toString();
		request.setAttribute("sdate_debut", sdate_debut);
		sdate_fin = date_fin.toString();
		request.setAttribute("sdate_fin", sdate_fin);
		sheure_debut = heureString;
		request.setAttribute("sheure_debut", heureString);
		sheure_fin = heureString;
		request.setAttribute("sheure_fin", heureString);
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
		
//		date= LocalDateTime.of(				*Plus performant
//							Integer.parseInt(dateString.substring(0,4)),
//							Integer.parseInt(dateString.substring(5,7)),
//							Integer.parseInt(dateString.substring(8,10)),
//							Integer.parseInt(timeString.substring(0,2)), 
//							Integer.parseInt(timeString.substring(3,5))
//						);
		
		return date;
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

}
