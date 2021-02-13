package fr.eni.projetEncheres.dal;


import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.bean.Enchere;
import fr.eni.projetEncheres.bean.Retrait;
import fr.eni.projetEncheres.dal.jdbc.ArticleVenduJdbcImpl;
import fr.eni.projetEncheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.projetEncheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.projetEncheres.dal.jdbc.RetraitDAOJdbcImpl;
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
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		ArticleVenduDAO articleVenduDAO = new ArticleVenduJdbcImpl();
		return articleVenduDAO;
	}
	
	public static DAO<Enchere> getEnchereDAO() {
		DAO<Enchere> enchereDAO = new EnchereDAOJdbcImpl();
		return enchereDAO;
	}
	
	
	public static DAO<Retrait> getRetraitDAO(){
		DAO<Retrait> retraitDAO = new RetraitDAOJdbcImpl();
		return retraitDAO;
	}

}
