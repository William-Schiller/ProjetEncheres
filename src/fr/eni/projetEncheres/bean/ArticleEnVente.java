package fr.eni.projetEncheres.bean;

import java.io.Serializable;

public class ArticleEnVente implements Serializable{
	private static final long serialVersionUID = 1L;

	private ArticleVendu article;
	private Enchere meilleurEnchere;
	private Utilisateur user;
	private String date_fin;
	
	public ArticleEnVente() {
	}
	
	

	public ArticleEnVente(ArticleVendu article, Enchere meilleurEnchere, Utilisateur user, String date_fin) {
		super();
		this.article = article;
		this.meilleurEnchere = meilleurEnchere;
		this.user = user;
		this.date_fin = date_fin;
	}

	

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	public Enchere getMeilleurEnchere() {
		return meilleurEnchere;
	}

	public void setMeilleurEnchere(Enchere meilleurEnchere) {
		this.meilleurEnchere = meilleurEnchere;
	}

	public String getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	@Override
	public String toString() {
		return "ArticleEnVente [article=" + article + ", meilleurEnchere=" + meilleurEnchere + ", user=" + user
				+ ", date_fin=" + date_fin + "]";
	}

	
}
