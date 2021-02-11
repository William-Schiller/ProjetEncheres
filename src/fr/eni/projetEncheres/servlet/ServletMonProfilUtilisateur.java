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
 * Servlet implementation class ServletMonProfilUtilisateur
 */
@WebServlet("/MonProfil")
public class ServletMonProfilUtilisateur extends HttpServlet {
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
			this.getServletContext().getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
		} 
		
		this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> listError = new ArrayList<>();
		
		String pseudo = request.getParameter("spseudo");
		String nom = request.getParameter("snom");
		String prenom = request.getParameter("sprenom");
		String email = request.getParameter("semail");
		String telephone = request.getParameter("stelephone");
		String rue = request.getParameter("srue");
		String codePostal = request.getParameter("scodePostal");
		String ville = request.getParameter("sville");
		
		String motDePasseActuel = request.getParameter("spasswordact");
		String motDePasseNouveau = request.getParameter("spasswordnew");
		String motDePasseConfirme = request.getParameter("spasswordconf");
		
		if(request.getParameter("supdate") != null && request.getParameter("supdate").equals("ok")) {
			Utilisateur utilisateurModif = (Utilisateur)request.getSession().getAttribute("myUser");
			
			if(!pseudo.isEmpty()) {
				utilisateurModif.setPseudo(pseudo);
			}
			if(!nom.isEmpty()) {
				utilisateurModif.setNom(nom);
			}
			if(!prenom.isEmpty()) {
				utilisateurModif.setPrenom(prenom);
			}
			if(!email.isEmpty()) {
				utilisateurModif.setEmail(email);
			}
			if(!telephone.isEmpty()) {
				utilisateurModif.setTelephone(telephone);
			}
			if(!rue.isEmpty()) {
				utilisateurModif.setRue(rue);
			}
			if(!codePostal.isEmpty()) {
				try {
					utilisateurModif.setCode_postal(Integer.parseInt(codePostal.trim()));
				} catch(NumberFormatException e){
					listError.add("Le code postal ne pas contenir de caractère alphabéthique");
				}
			}
			if(!ville.isEmpty()) {
				utilisateurModif.setVille(ville);
			}
			
			if(!motDePasseActuel.isEmpty() && !motDePasseNouveau.isEmpty() && !motDePasseConfirme.isEmpty()) {
				if(motDePasseActuel.equals(((Utilisateur)request.getSession().getAttribute("myUser")).getMot_de_passe())) {
					if(motDePasseNouveau.equals(motDePasseConfirme)) {
						utilisateurModif.setMot_de_passe(motDePasseConfirme);
					} else {
						listError.add("La confirmation de mot de passe est incorrect");
					}
				} else {
					listError.add("Le mot de passe est incorrect");
				}
				
			}
			
			if(!listError.isEmpty()) {
				listError.add("Modification du profil impossible");
			} else {
				try {
					utilisateurManager.updateUser(utilisateurModif);
				} catch (BLLException e) {
					listError = utilisateurManager.getListError();
					listError.add("Impossible de modifier le profil");
				}
			}
			
			request.setAttribute("listError", listError);
					
		}
		if(request.getParameter("sdelete") != null && request.getParameter("sdelete").equals("ok")) {
			this.getServletContext().getRequestDispatcher("/SuppressionCompte").forward(request, response);
		}
		
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
