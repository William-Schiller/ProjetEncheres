package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.eni.projetEncheres.bean.Enchere;
import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.bll.BLLException;
import fr.eni.projetEncheres.bll.EnchereManager;


/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet("/Encherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EnchereManager enchereManager;  

	public void init() throws ServletException {
		enchereManager = EnchereManager.getInstance();
    	super.init();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/detailsEnchere.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");
		int idNo_article = 0;
		int montant_enchere = 0;
		Enchere enchere = null;
		
		System.out.println("S1");

		List<String> listeErreurs = new ArrayList<>();
		
		if (!request.getParameter("idNo_article").isEmpty()) {
			idNo_article = Integer.parseInt(request.getParameter("idNo_article"));
			System.out.println("S2");
		}
		request.setAttribute("idNo_article", idNo_article);
		
		if (!request.getParameter("smonEnchere").isEmpty()) {
			montant_enchere = Integer.parseInt(request.getParameter("smonEnchere"));
			System.out.println("S3");
		}
		if (montant_enchere == 0) {
			request.getRequestDispatcher("/DetailEnchere").forward(request, response);
			System.out.println("S4");
		}
		
		System.out.println(idNo_article + " , " + montant_enchere);
		System.out.println(user.getNo_utlisateur());
		
		enchere = new Enchere(LocalDateTime.now(), montant_enchere, idNo_article, user.getNo_utlisateur());
		System.out.println("S45");
		try {
			enchereManager.ajoutEnchere(enchere, user);
			System.out.println("S5");
		} catch (BLLException e) {
			System.out.println("help ajoutEnchere servlet");
		}
		
		listeErreurs = enchereManager.getListError();
		if (!listeErreurs.isEmpty()) {
			request.getRequestDispatcher("/DetailEnchere").forward(request, response);
		}
		System.out.println("S6");
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		
	}

}
