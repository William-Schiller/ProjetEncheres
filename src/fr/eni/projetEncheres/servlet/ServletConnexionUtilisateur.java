package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.bll.BLLException;
import fr.eni.projetEncheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletConnexionUtilisateur
 */
@WebServlet("/Connexion")
public class ServletConnexionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager;
       
	
	public void init() throws ServletException {
		utilisateurManager = UtilisateurManager.getInstance();
    	super.init();
    }
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");
		
		if(user != null && !user.getPseudo().isEmpty()) {
			this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
		}

		
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response));

		List<String> listError = new ArrayList<>();
		Utilisateur user = null;
		String pseudo =null;
		String mot_de_passe = null;
		String passwordError = null;
		String pseudoError = null;
		
		if(!request.getParameter("spseudo").isEmpty() && !request.getParameter("spassword").isEmpty()) {
			pseudo = request.getParameter("spseudo");
			mot_de_passe = request.getParameter("spassword");
		} else {
			if(!request.getParameter("spseudo").isEmpty()) {
				pseudo = request.getParameter("spseudo");
				request.setAttribute("pseudo", pseudo);
				passwordError = "Renseigner un mot de passe";
			} else if(!request.getParameter("spassword").isEmpty()) {
				pseudoError = "Renseigner un pseudo";
			}
			request.setAttribute("passwordError", passwordError);
			request.setAttribute("pseudoError", pseudoError);
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		}
		
		try {
			user = utilisateurManager.connexionUser(pseudo, mot_de_passe);
		} catch (BLLException e) {
			request.setAttribute("pseudo", pseudo);
			listError = utilisateurManager.getListError();
			listError.add("Impossible de se connecter");
			request.setAttribute("listError", listError);
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		}

		HttpSession session = request.getSession();
		
		session.setAttribute("myUser", user);
		
		this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
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
