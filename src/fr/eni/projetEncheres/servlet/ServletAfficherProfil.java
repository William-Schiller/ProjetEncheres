package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.bll.UtilisateurManager;
import fr.eni.projetEncheres.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet("/Profil")
public class ServletAfficherProfil extends HttpServlet {
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
		
		if(!request.getParameter("sid").isEmpty()) {
			int no_utilisateur = Integer.parseInt(request.getParameter("sid"));
			try {
				 Utilisateur utilisateur =  utilisateurManager.postUser(no_utilisateur);

				request.setAttribute("utilisateur", utilisateur);
				
				Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");
				
				if(user.getPseudo().equals(utilisateur.getPseudo())) {
					this.getServletContext().getRequestDispatcher("/MonProfil").forward(request, response);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/profilutilisateur.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/Accueil").forward(request, response);
		}
		
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
