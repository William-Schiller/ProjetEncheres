package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.bll.UtilisateurManager;



@WebServlet("/inscription")
public class ServletSinscrire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager;
	
	//private UtilisateurDAO UtilisateurDAO = new UtilisateurDAO();	
	
	public void init() throws ServletException {
		utilisateurManager = UtilisateurManager.getInstance();
    	super.init();
    }

    public ServletSinscrire() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sinscrire.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<String> listeErreurs = new ArrayList<>();
		
		String pseudo = request.getParameter("pseudo");
		String prenom = request.getParameter("prenom");
		String telephone = request.getParameter("telephone");
		String postal = request.getParameter("postal");
		String mdp = request.getParameter("mdp");
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		String confirmation = request.getParameter("confirmation");
		
		
		if(!pseudo.isEmpty() && !prenom.isEmpty() && !telephone.isEmpty() && !postal.isEmpty() && 
				!mdp.isEmpty() && !nom.isEmpty() && !email.isEmpty() && !rue.isEmpty() && !ville.isEmpty() && !confirmation.isEmpty()) {
//		(pseudo != null || prenom != null || telephone != null || postal != null 
//				|| mdp != null || nom != null || email != null || rue != null || ville != null || confirmation != null) {
			
			Utilisateur u = new Utilisateur();

			u.setPseudo(pseudo);
			u.setPrenom(prenom);
			u.setTelephone(telephone);
			try {
				u.setCode_postal(Integer.parseInt(postal));
			} catch (NumberFormatException e) {
				listeErreurs.add("Le code postal doit être en chiffres");
			}
			if (mdp.equals(confirmation)){
				u.setMot_de_passe(mdp);
			} else {
				listeErreurs.add("Le mot de passe et la confirmation doivent être identiques");
			}	
			u.setNom(nom);
			u.setEmail(email);
			u.setRue(rue);
			u.setVille(ville);
			
			if (!listeErreurs.isEmpty()) {
				request.setAttribute("ListeErreurs", listeErreurs);
				this.getServletContext().getRequestDispatcher("/WEB-INF/sinscrire.jsp").forward(request, response);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sinscrire.jsp");
//				dispatcher.forward(request, response);
			} else {


				try {
					utilisateurManager.inscriptionUser(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/accueilConnecte.jsp").forward(request, response);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueilConnecte.jsp");
//				dispatcher.forward(request, response);
			}
		} 
		else {
			listeErreurs.add("Tous les champs doivent être remplis");
			request.setAttribute("listeErreurs", listeErreurs);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/sinscrire.jsp").forward(request, response);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sinscrire.jsp");
//			dispatcher.forward(request, response);

		
		}
		
	}

}
