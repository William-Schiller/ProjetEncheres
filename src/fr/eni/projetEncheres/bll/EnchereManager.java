package fr.eni.projetEncheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Enchere;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;
import fr.eni.projetEncheres.dal.jdbc.EnchereDAOJdbcImpl;

public class EnchereManager {
	private static EnchereManager enchereManager;
	private DAO<Enchere> enchereDAO;
	
	private static List<String> listError;
	
	private EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public static EnchereManager getInstance() {
		if(enchereManager == null) {
			enchereManager = new EnchereManager();
		}
		return enchereManager;
	}
	
	public List<String> getListError() {
		return listError;
	}
	
	
	//***************METHODE********************
	
	
	/**
	 * @author : DR
	 */
//	public static Enchere ajoutEnchere (Enchere enchere) throws BLLException {
//		listError = new ArrayList<>();
//		
		// recup article vendu grace a l'id d'article de l'enchere avec method selectbyid d'articlevendudao
		// recup toutes les encheres de l'article avec une method dans daojdbc lister encheres d'un article avec un id
		// selectbyid mais demander de ranger par ordre decroissant de montant et de limiter a 1 resultat
		// faire method dans encheredaojdbcimpl
	
		
//		checkEnchere(enchere.getMontant_enchere(), listError);
//		checkPoints(enchere.getMontant_enchere(), listError);
//		
//		if (!listError.isEmpty()) {
//			throw new BLLException("Echec ajoutEnchere : verification points et prix");
//		}
//		try {
//			EnchereDAOJdbcImpl.insert(enchere);
//		} catch (DALException e) {
//			throw new BLLException("Echec ajoutEnchere");
//		}
//	}
	
	
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
			listError.add("Vous ne disposez pas d'assez de crédit pour effectuer cette enchère. "
					+ "Votre crédit est de " + pointsPerso);
		}	
	}
	
}
