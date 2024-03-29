package fr.eni.projetEncheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    	request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sinscrire.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 
		
		List<String> listeErreurs = new ArrayList<>();
		
		String pseudo = request.getParameter("pseudo");
		if (pseudo!=null || !request.getParameter(pseudo).isEmpty()) {
			request.setAttribute("spseudo", pseudo);
		}
		String prenom = request.getParameter("prenom");
		if (prenom!=null || !request.getParameter(prenom).isEmpty()) {
			request.setAttribute("prenomForm", prenom);
		}
		String telephone = request.getParameter("telephone");
		if (telephone!=null || !request.getParameter(telephone).isEmpty()) {
			request.setAttribute("telephoneForm", telephone);
		}
		String postal = request.getParameter("postal");
		if (postal!=null || !request.getParameter(postal).isEmpty()) {
			try {
				int code_postal = Integer.parseInt(postal);
				request.setAttribute("postalForm", code_postal);
			} catch (Exception e) {
			}
		}
		String mdp = request.getParameter("mdp");
		String nom = request.getParameter("nom");
		if (nom!=null || !request.getParameter(nom).isEmpty()) {
			request.setAttribute("nomForm", nom);
		}
		String email = request.getParameter("email");
		if (email!=null || !request.getParameter(email).isEmpty()) {
			request.setAttribute("emailForm", email);
		}
		String rue = request.getParameter("rue");
		if (rue!=null || !request.getParameter(rue).isEmpty()) {
			request.setAttribute("rueForm", rue);
		}
		String ville = request.getParameter("ville");
		if (ville!=null || !request.getParameter(ville).isEmpty()) {
			request.setAttribute("villeForm", ville);
		}
		String confirmation = request.getParameter("confirmation");
		
		
		if(!pseudo.isEmpty() && !prenom.isEmpty() && !telephone.isEmpty() && !postal.isEmpty() && 
				!mdp.isEmpty() && !nom.isEmpty() && !email.isEmpty() && !rue.isEmpty() && !ville.isEmpty() && !confirmation.isEmpty()) {
//		(pseudo != null && prenom != null && telephone != null && postal != null 
//				&& mdp != null && nom != null && email != null && rue != null && ville != null && confirmation != null) {
			
			Utilisateur u = new Utilisateur();

			u.setPseudo(pseudo);
			u.setPrenom(prenom);
			try {
				Integer.parseInt(telephone.trim());
				u.setTelephone(telephone.trim());
			} catch (NumberFormatException e) {
				listeErreurs.add("Le téléphone doit être en chiffres");
			}
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
				request.setAttribute("listeDesErreurs", listeErreurs);
				this.getServletContext().getRequestDispatcher("/WEB-INF/sinscrire.jsp").forward(request, response);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sinscrire.jsp");
//				dispatcher.forward(request, response);
			} else {
				try {
					utilisateurManager.inscriptionUser(u);
					u = utilisateurManager.connexionUser(u.getPseudo(), u.getMot_de_passe());
					HttpSession session = request.getSession();
					session.setAttribute("myUser", u);
				} catch (Exception e) {
					listeErreurs = utilisateurManager.getListError();
					request.setAttribute("listeDesErreurs", listeErreurs);
					this.getServletContext().getRequestDispatcher("/WEB-INF/sinscrire.jsp").forward(request, response);
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueilConnecte.jsp");
//				dispatcher.forward(request, response);
			}
		} else {
			listeErreurs.add("Tous les champs doivent être remplis");
			request.setAttribute("listeDesErreurs", listeErreurs);
			this.getServletContext().getRequestDispatcher("/WEB-INF/sinscrire.jsp").forward(request, response);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sinscrire.jsp");
//			dispatcher.forward(request, response);

		
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
