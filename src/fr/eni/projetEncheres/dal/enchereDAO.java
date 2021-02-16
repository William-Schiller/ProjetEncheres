package fr.eni.projetEncheres.dal;

import java.sql.SQLException;

import fr.eni.projetEncheres.bean.Enchere;

public interface enchereDAO extends DAO<Enchere> {
	
	public void recupEnchereMax(int id_article) throws DALException, SQLException;
	
}
