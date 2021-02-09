package fr.eni.projetEncheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;
import fr.eni.projetEncheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	//test william 
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
	
	
	/**aureliensuel
	 * 
	 */
	public Utilisateur selectUser(int id) throws BLLException {
	
		Utilisateur user =null;
		
		try {
			user = utilisateurDAO.selectByID(id);
			
			
		}catch (Exception e) {
			throw new BLLException("echec de l'id ");
		}
		
		
		return user;
		
		
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
		
		if(listError != null) {
			throw new BLLException("Echec connexionUser : vérification pseudo et mot de passe");
		}
		
		try {
			user = utilisateurDAO.selectByPseudo(pseudo, mot_de_passe);
		} catch (DALException e) {
			throw new BLLException("Echec connexionUser");
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
	
	
	
	
	
}
