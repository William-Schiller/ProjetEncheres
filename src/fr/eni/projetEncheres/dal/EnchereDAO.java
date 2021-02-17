package fr.eni.projetEncheres.dal;

import java.sql.SQLException;

import fr.eni.projetEncheres.bean.Enchere;

public interface EnchereDAO extends DAO<Enchere> {
	
	public Enchere recupEnchereMax(int id_article) throws DALException;
	 
}
