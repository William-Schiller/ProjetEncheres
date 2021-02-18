package fr.eni.projetEncheres.bll;

import java.util.ArrayList;
import java.util.List;


import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;


public class CategorieManager {
	
	private static CategorieManager categorieManager ;
	
	private DAO<Categorie> categorieDAO;
	
	
	private CategorieManager() {
		this.categorieDAO =  DAOFactory.getCategorieDAO();
		
	}
		
	public static CategorieManager getInstance() {
		if(categorieManager == null) {
			categorieManager = new CategorieManager();
		}
		return categorieManager;
	}
			
	public List<Categorie> selectall () throws BLLException{
		   List<Categorie> list = new ArrayList<Categorie>();
				
			try {
				list = categorieDAO.selectAll();
					
				} catch (DALException e) {
					e.printStackTrace();
				}
			return list;
		}


	public Categorie selectNo_Categorie(int id) throws BLLException {
	    Categorie categorie= null;
	
	    try {
	    	categorie = categorieDAO.selectByID(id);
		
		} catch (DALException e) {
	
			throw new BLLException("echec method selectByCategorie");
		}
		
	    return categorie;
	}

	
	
}