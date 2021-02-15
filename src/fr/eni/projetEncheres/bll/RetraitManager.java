package fr.eni.projetEncheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import fr.eni.projetEncheres.bean.Retrait;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;

public class RetraitManager {

	
	private static RetraitManager retraitManager;
	
	private DAO <Retrait> retraitDAO;
	
	
	private RetraitManager() {
		this.retraitDAO =  DAOFactory.getRetraitDAO();
		
	}
	
	
	public static RetraitManager getInstance() {
		if(retraitManager == null) {
			retraitManager = new RetraitManager();
		}
		return retraitManager;
	}
	
	
	public void insertRetrait(Retrait t) throws BLLException {
		Retrait insertretrait = null;
		
		
		try {
			
		  retraitDAO.insert(t);
			
		
		} catch (DALException e) {
			throw new BLLException("Echec method insert()");
		
		
		}
		
	}	
				
			

	
public void deleteRetrait(Retrait t) throws BLLException {
		
		try {
			
			retraitDAO.delete(t.getNo_retrait());
		
		} catch (DALException e) {
			
			throw new BLLException("Echec deleteRetrait");
		}
		
	}
	
	
}
