package fr.eni.projetEncheres.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Enchere;
import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.dal.ArticleVenduDAO;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAOFactory;
import fr.eni.projetEncheres.dal.EnchereDAO;
import fr.eni.projetEncheres.dal.UtilisateurDAO;

public class EnchereManager {
	private static EnchereManager enchereManager;
	private EnchereDAO enchereDAO;
	private ArticleVenduDAO articleVenduDAO;
	private UtilisateurDAO utilisateurDAO;
	
	private static List<String> listError;
	
	private EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static EnchereManager getInstance() {
		if(enchereManager == null) {
			enchereManager = new EnchereManager();
		}
		return enchereManager;
	}
	
	public static List<String> getListError() {
		return listError;
	}
	
	
	//***************METHODE********************

	/**
	 * @author : WS
	 */
	public Enchere derniereEnchere(ArticleVendu av) throws BLLException {
		Enchere enchereMax = null;
		
		try {
			enchereMax = enchereDAO.recupEnchereMax(av.getNo_article());
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur method derniereEnchere");
		}
		
		return enchereMax;
	}
	
	/**
	 * @author : DR
	 */
	public void ajoutEnchere (Enchere enchere, Utilisateur utilisateur) throws BLLException {
		listError = new ArrayList<>();
		
		Enchere enchereMax = null;
		ArticleVendu article = null;
		int prixMin = 0;
		Utilisateur dernierEncherisseur = null;
		
		try {
			enchereMax = enchereDAO.recupEnchereMax(enchere.getNo_article());
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		
		try {
			article = articleVenduDAO.selectByID(enchere.getNo_article());
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		
		if (enchereMax == null) {
			prixMin = article.getPrix_initial();
		} else {
			prixMin = enchereMax.getMontant_enchere();
		}
		
		if(article.getDate_fin_encheres().isBefore(LocalDateTime.now())) {
			listError.add("L'enchere est clôturé");
		}
		
		checkEnchere(enchere.getMontant_enchere(), prixMin, listError);
		checkPoints(enchere.getMontant_enchere(), utilisateur.getCredit(), listError);

		if (!listError.isEmpty()) {
			throw new BLLException("Echec ajoutEnchere1");
		}
		
		try {
			enchereDAO.insert(enchere);
			utilisateur.setCredit(utilisateur.getCredit() - enchere.getMontant_enchere());
			utilisateurDAO.update(utilisateur);
			
			if (enchereMax != null) {
				dernierEncherisseur = utilisateurDAO.selectByID(enchereMax.getNo_utilisateur());
				dernierEncherisseur.setCredit(dernierEncherisseur.getCredit() + enchereMax.getMontant_enchere());
				utilisateurDAO.update(dernierEncherisseur);
				
			}
		} catch (DALException e) {
			throw new BLLException("Echec ajoutEnchere2");
		}
		
	}
	
	
	//***************VERIFICATION******************
	
	
	/**
	 * @author : DR
	 */
	public void checkEnchere(int enchere, int tarifActuel, List<String> listError){
		if(enchere < tarifActuel) {
			listError.add("Pour enchérir, vous devez proposer un prix supérieur à l'enchère actuelle");
		}	
	}
	
	public void checkPoints(int enchere, int pointsPerso, List<String> listError){
		if(enchere > pointsPerso) {
			listError.add("Vous ne disposez pas d'assez de crédit pour effectuer cette enchère.");
			listError.add("Votre crédit est de " + pointsPerso + " points");
		}	
	}
	
}
