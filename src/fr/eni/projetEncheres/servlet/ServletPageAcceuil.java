package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.bll.ArticleVenduManager;
import fr.eni.projetEncheres.bll.BLLException;
import fr.eni.projetEncheres.bll.CategorieManager;



/**
 * Servlet implementation class ServletPageAcceuil
 */
@WebServlet("/Accueil")
public class ServletPageAcceuil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private ArticleVenduManager articleVenduManager;
	private CategorieManager categorieManager;
	
	public void init() throws ServletException {
		articleVenduManager = ArticleVenduManager.getInstance();
		categorieManager = CategorieManager.getInstance();
		super.init();
	}
	
	
	
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response));

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		List<ArticleVendu> listeArticle = null ;
		List<Categorie> listeCategorie = null;
		
		try {
			listeCategorie = categorieManager.selectall();
			request.setAttribute("listeCategorie", listeCategorie);
		
		}catch (BLLException e) {
			e.printStackTrace();
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}	
		try {
			listeArticle = articleVenduManager.selectall();
			
			
		}catch (BLLException e) {
		
		}
		
		
		
		
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
