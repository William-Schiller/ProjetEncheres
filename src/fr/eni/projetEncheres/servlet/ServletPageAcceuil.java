package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bean.ArticleEnVente;
import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.bean.Enchere;
import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.bll.ArticleVenduManager;
import fr.eni.projetEncheres.bll.BLLException;
import fr.eni.projetEncheres.bll.CategorieManager;
import fr.eni.projetEncheres.bll.EnchereManager;
import fr.eni.projetEncheres.bll.UtilisateurManager;



/**
 * Servlet implementation class ServletPageAcceuil
 */
@WebServlet("/Accueil")
public class ServletPageAcceuil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleVenduManager articleVenduManager;
	private CategorieManager categorieManager;
	private EnchereManager enchereManager;
	private UtilisateurManager utilisateurManager;

	public void init() throws ServletException {
		articleVenduManager = ArticleVenduManager.getInstance();
		categorieManager = CategorieManager.getInstance();
		enchereManager = EnchereManager.getInstance();
		utilisateurManager = UtilisateurManager.getInstance();
		super.init();
	}




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response));
	
		List<ArticleVendu> listeArticleVendu = new ArrayList<>();
		List<ArticleEnVente> listeArticle = new ArrayList<>();
		List<Categorie> listeCategorie = new ArrayList<>();
		String keyword = null;
		int no_categorie = 0;
		
		Utilisateur myUser = (Utilisateur) request.getSession().getAttribute("myUser");
		
		String sachatsVentes = null;
		
		//Recuperer les parametres de recherche avancé 
		if(request.getParameter("sachatsVentes") != null && !request.getParameter("sachatsVentes").isEmpty()) {
			sachatsVentes = request.getParameter("sachatsVentes");
		}
		
		if(request.getParameter("scategorie") != null && !request.getParameter("scategorie").isEmpty()) {
		no_categorie = Integer.parseInt(request.getParameter("scategorie"));
		request.setAttribute("no_categorie", no_categorie);
		}

		if(request.getParameter("skeyword") != null && !request.getParameter("skeyword").isEmpty()) {
			keyword = request.getParameter("skeyword");
			request.setAttribute("keyword", keyword);
		}
					
		try {
			
			listeCategorie = categorieManager.selectall();
			for (Categorie categorie : listeCategorie) {
				System.out.println(categorie.toString());
			}
			request.setAttribute("listeCategorie", listeCategorie);
			
			
				}catch (BLLException e) {
					System.out.println("beug categorie");
				}
		
		//affichage article sans recherche achats/mesventes
		if(sachatsVentes == null || sachatsVentes.equals("enchereouverte")) {
			try {
				if(keyword == null && no_categorie == 0) {
					listeArticleVendu = articleVenduManager.selectAllArticle();
				}else if(keyword == null && no_categorie != 0){
					listeArticleVendu = articleVenduManager.selectByCategorie(no_categorie);
				}else if(keyword != null && no_categorie ==0) {
					listeArticleVendu = articleVenduManager.selectByArticle(keyword);
				}else if(keyword !=null && no_categorie !=0) {
					listeArticleVendu = articleVenduManager.selectByArticleAndCategorie(keyword, no_categorie);
				}
				
				if(!listeArticleVendu.isEmpty()) {
					for(ArticleVendu a : listeArticleVendu) {
						Enchere enchere = null;
						Utilisateur user = null;
						LocalDate date = a.getDate_fin_encheres().toLocalDate();
						LocalTime heure = a.getDate_fin_encheres().toLocalTime();
						StringBuffer datefmt = new StringBuffer();
						datefmt.append(date.toString()).append(" ").append(heure.toString());
						enchere = enchereManager.derniereEnchere(a);
						user = utilisateurManager.postUser(a.getNo_utilisateur());
						listeArticle.add(new ArticleEnVente(a, enchere, user, datefmt.toString()));
					}
			}
					
				request.setAttribute("listeArticle", listeArticle);
			}catch (BLLException e) {
				System.out.println("bugArticle");
			}
		} else if (sachatsVentes.equals("ventenondebutees")) {
			try {
				listeArticleVendu = articleVenduManager.selectAllArticleVenteNonDebutee(myUser.getNo_utlisateur());

				
				if(!listeArticleVendu.isEmpty()) {
					for(ArticleVendu a : listeArticleVendu) {
						Enchere enchere = null;
						Utilisateur user = null;
						LocalDate date = a.getDate_fin_encheres().toLocalDate();
						LocalTime heure = a.getDate_fin_encheres().toLocalTime();
						StringBuffer datefmt = new StringBuffer();
						datefmt.append(date.toString()).append(" ").append(heure.toString());
						enchere = enchereManager.derniereEnchere(a);
						user = utilisateurManager.postUser(a.getNo_utilisateur());
						listeArticle.add(new ArticleEnVente(a, enchere, user, datefmt.toString()));
					}
			}
					
				request.setAttribute("listeArticle", listeArticle);
			}catch (BLLException e) {
				System.out.println("bugArticle");
			}
		}else if (sachatsVentes.equals("vente")) {
			//TODO RECUPERER LES INFOS POUR SELECTION ACHATS
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
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
