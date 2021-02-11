package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.bll.BLLException;
import fr.eni.projetEncheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletSuppressionCompte
 */
@WebServlet("/SuppressionCompte")
public class ServletSuppressionCompte extends HttpServlet {
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

		this.getServletContext().getRequestDispatcher("/WEB-INF/supprimerMonCommpte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("sfinaldelete")==null) {
			doGet(request, response);
		}
		
		String dropValidate = request.getParameter("sfinaldelete");

		if(dropValidate.equals("ok")) {
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");
			try {
				utilisateurManager.deleteUser(user);
				request.getSession().removeAttribute("myUser");
			} catch (BLLException e) {
				List<String> listError = new ArrayList<>();
				listError.add("Echec de la suppression");
				request.setAttribute("listError", listError);
				this.getServletContext().getRequestDispatcher("/MonProfil").forward(request, response);
			}
			this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/MonProfil").forward(request, response);
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
