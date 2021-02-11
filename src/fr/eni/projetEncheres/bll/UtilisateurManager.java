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
	
	public List<String> getListError() {
		return listError;
	}
	
	//******************* METHODE : *******************************
	
	/**
	 * @author : sw
	 * @throws BLLException 
	 */
	public Utilisateur connexionUser(String pseudo, String mot_de_passe) throws BLLException {
		listError = new ArrayList<>();
		Utilisateur user = null;
		
		checkPseudo(pseudo, listError);
		checkEmail(pseudo, listError);
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
	
	/**
	 * @author : ws
	 */
	public void updateUser(Utilisateur u) throws BLLException {
		listError = new ArrayList<>();
		
		checkPseudo(u.getPseudo(), listError);
		checkNom(u.getNom(), listError);
		checkPrenom(u.getPrenom(), listError);
		checkEmail(u.getEmail(), listError);
		checkTelephone(u.getTelephone(), listError);
		checkRue(u.getRue(), listError);
		checkCodePostal(String.valueOf(u.getCode_postal()), listError);
		checkVille(u.getVille(), listError);
		checkPassword(u.getMot_de_passe(), listError);
		
		if(!listError.isEmpty()) {
			throw new BLLException("Echec updateUser : vérification des attributs");
		}
		
		try {
			utilisateurDAO.update(u);
		} catch (DALException e) {
			throw new BLLException("Echec updateUser");
		}
		
	}
	
	/**
	 * 
	 * @author : DR
	 * @throws DALException 
	 */
	public void inscriptionUser(Utilisateur u) throws BLLException {
		listError = new ArrayList<>();
		
		checkPseudoUnique(u.getPseudo(), listError);
		checkEmailUnique(u.getEmail(), listError);
		
		if (!listError.isEmpty()) {
			throw new BLLException("Echec inscriptionUser : verification pseudo et email");
		}
		try {
			utilisateurDAO.insert(u);
		} catch (DALException e) {
			throw new BLLException("Echec inscriptionUser");
		}
	}
	
	
	//**************** VERIFICATION ************************************
	
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
	public void checkNom(String nom, List<String> listError){
		if(nom.length() > 30) {
			listError.add("Le nom ne doit pas dépasser 30 caractères");
		}	
	}
	
	/**
	 * @author : sw
	 */
	public void checkPrenom(String prenom, List<String> listError){
		if(prenom.length() > 30) {
			listError.add("Le prenom ne doit pas dépasser 30 caractères");
		}	
	}
	
	/**
	 * @author : sw
	 */
	public void checkEmail(String email, List<String> listError){
		if(email.length() > 50) {
			listError.add("L'Email ne doit pas dépasser 50 caractères");
		}	
	}
	
	/**
	 * @author : sw
	 */
	public void checkTelephone(String telephone, List<String> listError){
		if(telephone.length() > 15) {
			listError.add("Le numero de telephone ne doit pas dépasser 15 caractères");
		}	
	}
	
	/**
	 * @author : sw
	 */
	public void checkRue(String rue, List<String> listError){
		if(rue.length() > 100) {
			listError.add("La rue ne doit pas dépasser 100 caractères");
		}	
	}
	
	/**
	 * @author : sw
	 */
	public void checkCodePostal(String codePostal, List<String> listError){
		if(codePostal.length() > 10) {
			listError.add("Le code postal ne doit pas dépasser 10 caractères");
		}	
	}
	
	/**
	 * @author : sw
	 */
	public void checkVille(String ville, List<String> listError){
		if(ville.length() > 30) {
			listError.add("La ville ne doit pas dépasser 30 caractères");
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
	 * @author : dr
	 * @throws BLLException 
	 */
	public boolean checkPseudoUnique(String pseudo, List<String> listError) throws BLLException{
		boolean verifPseudo = true;
		List<Utilisateur> listeUtilisateur = new ArrayList<>();
		try {
			listeUtilisateur = utilisateurDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec checkPseudoUnique");
		}
		
		for (Utilisateur utilisateur : listeUtilisateur) {
			if (utilisateur.getPseudo().equals(pseudo)) {
				verifPseudo = false;
				break;
			}	
		}
		if (verifPseudo == false) {
			listError.add("Ce pseudo existe déjà");
		}
		return verifPseudo;	
	}
	
	public boolean checkEmailUnique(String email, List<String> listError) throws BLLException{
		boolean verifEmail = true;
		List<Utilisateur> listeUtilisateur = new ArrayList<>();
		try {
			listeUtilisateur = utilisateurDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec checkEmailUnique");
		}
		
		for (Utilisateur utilisateur : listeUtilisateur) {
			if (utilisateur.getEmail().equals(email)) {
				verifEmail = false;
				break;
			}	
		}
		if (verifEmail == false) {
			listError.add("Cet email est déjà utilisé");
		}
		return verifEmail;	
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

