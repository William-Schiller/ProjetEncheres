package fr.eni.projetEncheres.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAOFactory;
import fr.eni.projetEncheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private Logger logger = EncheresLogger.getLogger("UtilisateurManager");
	
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
	public Utilisateur inscriptionUser(Utilisateur u) throws BLLException {
        Utilisateur user = null;
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
	
	//TODO méthode checkUniquePseudo renvois un boolean
	public void checkUniquePseudo(String pseudo, List<String> listError){
		
			
	}
	
	//TODO méthode checkUniqueEmail  renvois un boolean

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
