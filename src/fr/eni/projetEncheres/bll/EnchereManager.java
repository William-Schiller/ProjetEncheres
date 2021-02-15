package fr.eni.projetEncheres.bll;

import java.util.List;

import fr.eni.projetEncheres.bean.Enchere;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;

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
	
	
//	public static Enchere ajoutEnchere (Enchere enchere) throws BLLException {
//
//
//		if (!listError.isEmpty()) {
//			throw new BLLException("Echec ajoutEnchere : verification");
//		}
//		try {
//			enchereDAO.insert(enchere);
//		} catch (DALException e) {
//			throw new BLLException("Echec ajoutEnchere");
//		}
//		return enchere;
//		
//	}
	
	
	//***************VERIFICATION******************
	
	
}
