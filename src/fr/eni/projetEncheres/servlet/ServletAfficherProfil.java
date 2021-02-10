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
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherProfil() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Usage pratique plus tard : recupèrer le parametre id utilisateur (on arrivera à cette servlet après avoir clické sur un utilisateur !
//		Utilisateur user = request.getParameter("Utilisateur clicker");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/profilutilisateur.jsp").forward(request, response);
	
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             
		int no_utilisateur = Integer.parseInt(request.getParameter("sid"));
		try {
			
			 Utilisateur utilisateur =  utilisateurManager.postUser(no_utilisateur);
			 
		

			request.setAttribute("utilisateur", utilisateur);
			
		} catch (Exception e) {
		
		}
		
		doGet(request, response);
	}

}
