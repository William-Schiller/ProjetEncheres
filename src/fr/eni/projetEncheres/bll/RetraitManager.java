package fr.eni.projetEncheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import fr.eni.projetEncheres.bean.Retrait;
import fr.eni.projetEncheres.bean.Utilisateur;
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


	public void insertRetrait(Retrait t) throws BLLException{
		boolean verifRetraitExist = false;
		List<Retrait> listeRetrait = new ArrayList<>();


		//Recuperer la liste complete des retrait

		try {
			listeRetrait = retraitDAO.selectAll();
		} catch (DALException e1) {
			throw new BLLException("Echec method insertRetrait() a l'appel du DAO selectaLL");

		}

		//tester tous le contenue de la liste pour vérifier si les retrait existe
		for (Retrait retrait : listeRetrait) {
			if(t.getVille().equals(retrait.getVille()) && t.getCode_postale() == retrait.getCode_postale() && t.getRue().equals(retrait.getRue())) {
				//Vérifier si les parametre de t (test le retrait passé en parametre de la methode) sont egaux aux parametre de retrait ( retrait est un element retrait de la liste complete des retrait)
				//si le retrait existe recuperer le retrait dans une variable
				t.setNo_retrait(retrait.getNo_retrait());
				verifRetraitExist = true;
				break;
			}
		
		}

		if (!verifRetraitExist) {
			try {
				retraitDAO.insert(t);
			} catch (DALException e) {
				throw new BLLException("Echec method insertRetrait()");
			}
		}


	}	

	/**
	 * 
	 * @author : AS
	 */
	public Retrait selectById(int id) throws BLLException {
		Retrait r = new Retrait();
		
		try {

			r = retraitDAO.selectByID(id);

		} catch (DALException e) {
			throw new BLLException("Echec selectById");
		}
		return r;
	}


	/**
	 * 
	 * @author : AS
	 */
	public void deleteRetrait(Retrait t) throws BLLException {

		try {
			retraitDAO.delete(t.getNo_retrait());

		} catch (DALException e) {

			throw new BLLException("Echec deleteRetrait");
		}

	}


}
