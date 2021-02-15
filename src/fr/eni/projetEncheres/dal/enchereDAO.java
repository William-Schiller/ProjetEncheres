package fr.eni.projetEncheres.dal;

import fr.eni.projetEncheres.bean.Enchere;

public interface enchereDAO extends DAO<Enchere> {
	
	public void selectById(int montant_enchere, int prix_initial) throws DALException;
	
}
