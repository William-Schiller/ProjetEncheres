package fr.eni.projetEncheres.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Enchere;
import fr.eni.projetEncheres.bll.ArticleVenduManager;
import fr.eni.projetEncheres.bll.BLLException;
import fr.eni.projetEncheres.bll.CategorieManager;
import fr.eni.projetEncheres.bll.EnchereManager;
import fr.eni.projetEncheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletAfficherDetailEnchere
 */
@WebServlet("/DetailEnchere")
public class ServletAfficherDetailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurManager utilisateurManager;
	private ArticleVenduManager articleVenduManager;
	private CategorieManager categorieManager;
	private EnchereManager enchereManager;
       
	public void init() throws ServletException {
		articleVenduManager = ArticleVenduManager.getInstance();
		utilisateurManager = UtilisateurManager.getInstance();
		categorieManager = CategorieManager.getInstance();
		enchereManager = EnchereManager.getInstance();
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 
		
		int no_article = 0;
		ArticleVendu article = null;
		Enchere derniereEnchere = null;
		
		if(!request.getParameter("sno_article").isEmpty()) {
			no_article = Integer.parseInt(request.getParameter("sno_article"));
			request.setAttribute("sno_article", no_article);
		}
		
		try {
			articleVenduManager.selectArticleById(no_article);
			
			// TODO rechercher la derniere enchere faite !!
			derniereEnchere = new Enchere(); // A Supprimer
			
			request.setAttribute("article", article);
			if(derniereEnchere != null) {
				request.setAttribute("derniereEnchere", derniereEnchere);
			}
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/detailEnchere.jsp").forward(request, response);
			
		} catch (BLLException e) {
			e.printStackTrace(); // TODO
		}
		
		this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 
		
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
