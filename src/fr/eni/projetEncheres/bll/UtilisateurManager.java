package fr.eni.projetEncheres.bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAOFactory;
import fr.eni.projetEncheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private static UtilisateurManager utilisateurManager;
	private UtilisateurDAO utilisateurDAO;
	
	private static List<String> listError;
	
	private UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static UtilisateurManager getInstance() {
		if(utilisateurManager == null) {
			utilisateurManager = new UtilisateurManager();
		}
		return utilisateurManager;
	}
	
	/**
	 * @author : sw
	 * @throws BLLException 
	 */
	public Utilisateur connexionUser(String pseudo, String mot_de_passe) throws BLLException {
		listError = new ArrayList<>();
		Utilisateur user = null;
		
		checkPseudo(pseudo, listError);
		checkPassword(mot_de_passe, listError);
		
		if(!listError.isEmpty()) {
			throw new BLLException("Echec connexionUser : vérification pseudo et mot de passe");
		}
		try {
			user = utilisateurDAO.selectByPseudo(pseudo, mot_de_passe);
		} catch (DALException e) {
			throw new BLLException("Echec connexionUser");
		}
		
		if(user == null) {
			throw new BLLException("utilisateur inexistant");
		}
		return user;
	}
	
	public List<String> getListError() {
		return listError;
	}
	
	/**
	 * @author : sw
	 */
	public void checkPseudo(String pseudo, List<String> listError){
		if(pseudo.length() > 30) {
			listError.add("Le pseudonyme ne doit pas dépasser 30 caractères");
		}
			
	}
	
	/**
	 * @author : sw
	 */
	public void checkPassword(String mot_de_pass, List<String> listError){
		if(mot_de_pass.length() > 30) {
			listError.add("Le mot de passe ne doit pas dépasser 30 caractères");
		}
			
	}
	
	/**
	 * 
	 * @author : DR
	 */
	//
	public Utilisateur inscriptionUser(Utilisateur u) throws BLLException {
//        verifEmail(u);
//        verifPseudo(u);
//        boolean verifEmail = verifEmail(u.getEmail());
//        boolean verifPseudo = verifPseudo(u.getPseudo());
//
//        if ((verifEmail) & (verifPseudo)) {
//            throw new Exception("L'email et le pseudo existent déjà");
//        } else if ((verifEmail) & (!verifPseudo)) {
//            throw new Exception("L'email existe déjà");
//        } else if ((!verifEmail) & (verifPseudo)) {
//            throw new Exception("Ce pseudo est déjà pris, choisissez-en un autre");
//        }
        
        try {
        	utilisateurDAO.insert(u);
		} catch (DALException e) {
				e.printStackTrace();
		}          
        return u;
    }
	
	//méthode pseudo unique renvoi un boolean
	public boolean checkUniquePseudo(String pseudo, List<String> listError) throws BLLException{
		boolean verifPseudo = true;
		List<Utilisateur> liste = new ArrayList<>();
		
		try {
			liste = utilisateurDAO.selectAll();
		} catch (DALException e1) {
			throw new BLLException();
			// TODO message erreur
		}
		
		for (Utilisateur u : liste) {
			if (u.getPseudo().equals(pseudo)) {
				verifPseudo = false;
				break;
			}
		}
	    return verifPseudo;		
	}
	
	//méthode email unique renvoi un boolean
	public boolean checkUniqueEmail(String email, List<String> listError) throws BLLException{
		boolean verifEmail = true;
		List<Utilisateur> liste = new ArrayList<>();
		
		try {
			liste = utilisateurDAO.selectAll();
		} catch (DALException e1) {
			throw new BLLException();
			// TODO message erreur
		}
		for (Utilisateur u : liste) {
			if (u.getEmail().equals(email)) {
				verifEmail = false;
				break;
			}
		}
	    return verifEmail;	
	}
	
	
	
//	private void verifPseudo(String pseudo) throws Exception {
//		String characteres = "[a-zA-Z\\d]*";
//        Pattern p = Pattern.compile(characteres);
//        Matcher m = p.matcher(pseudo.getPseudo());
//        boolean verifPseudo = m.matches();
//        if (!verifPseudo) {
//            logger.severe("Le pseudo doit être au format alpha-numérique " + pseudo.getPseudo());
//            throw new Exception("Le pseudo doit être au format alpha numérique");
//        }	
//	}
//	
//	private void verifEmail(String email) throws Exception {
//		String characteres = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
//        Pattern p = Pattern.compile(characteres);
//        Matcher m = p.matcher(email.getEmail());
//        boolean verifEmail = m.matches();
//        if (!verifEmail) {
//            logger.severe("Tentative de création de compte avec un email incorrect: " + email.getEmail());
//            throw new Exception("L'adresse email n'est pas dans un format valide");
//        }
//	}


}

