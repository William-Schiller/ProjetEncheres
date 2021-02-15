package fr.eni.projetEncheres.dal;

import java.util.List;

import fr.eni.projetEncheres.bean.ArticleVendu;

public interface ArticleVenduDAO extends DAO<ArticleVendu> {
	
	public List<ArticleVendu> selectByNoCategorie(int idCategorie) throws DALException;
	
	public List<ArticleVendu> selectByKeyWordAndNoCategorie(String keyWord, int idCategorie) throws DALException;

}
