package fr.eni.projetEncheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.bll.UtilisateurManager;



@WebServlet("/inscription")
public class ServletSinscrire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private UtilisateurDAO UtilisateurDAO = new UtilisateurDAO();

    public ServletSinscrire() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sinscrire.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pseudo = request.getParameter("pseudo");
		String prenom = request.getParameter("prenom");
		String telephone = request.getParameter("telephone");
		int postal = Integer.parseInt(request.getParameter("postal"));
		String mdp = request.getParameter("mdp");
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		
		Utilisateur u = new Utilisateur();
		u.setPseudo(pseudo);
		u.setPrenom(prenom);
		u.setTelephone(telephone);
		u.setCode_postal(postal);
		u.setMot_de_passe(mdp);
		u.setNom(nom);
		u.setEmail(email);
		u.setRue(rue);
		u.setVille(ville);


		try {
			UtilisateurManager.inscriptionUser(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueilConnecte.jsp");
		dispatcher.forward(request, response);
		
	}

}
