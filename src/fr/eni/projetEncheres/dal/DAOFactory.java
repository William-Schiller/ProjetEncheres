package fr.eni.projetEncheres.dal;


import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.projetEncheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();
		
		return utilisateurDAO;
	}
	
	public static DAO<Categorie> getCategorieDAO() {
		DAO<Categorie> categorieDAO = new CategorieDAOJdbcImpl();
		return categorieDAO;
	}

}
