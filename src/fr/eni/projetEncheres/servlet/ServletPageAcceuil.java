package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.bean.Utilisateur;
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
	
		List<ArticleVendu> listeArticle = new ArrayList<>() ;
		List<Categorie> listeCategorie = new ArrayList<>() ;
		String keyword = null;
		int no_categorie = 0;
		
		
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
		
		try {
			if(keyword == null && no_categorie == 0) {
				listeArticle = articleVenduManager.selectAllArticle();
			}else if(keyword == null && no_categorie != 0){
				listeArticle = articleVenduManager.selectByCategorie(no_categorie);
			}else if(keyword != null && no_categorie ==0) {
				listeArticle = articleVenduManager.selectByArticle(keyword);
			}else if(keyword !=null && no_categorie !=0) {
				listeArticle = articleVenduManager.selectByArticleAndCategorie(keyword, no_categorie);
			}
			request.setAttribute("listeArticle", listeArticle);
		}catch (BLLException e) {
			System.out.println("bugArticle");
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
