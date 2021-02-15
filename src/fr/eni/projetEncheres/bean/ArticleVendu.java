package fr.eni.projetEncheres.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ArticleVendu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int no_article;
	private String nom_article;
	private String description;
	private LocalDateTime date_debut_encheres;
	private LocalDateTime date_fin_encheres;
	private int prix_initial;
	private int prix_vente;
	private String image;
	private int no_utilisateur;
	private int no_categorie;
	private int no_retrait;
	
	public ArticleVendu() {
	}
	
	/**
	 * Sans id et prix de vente
	 * 
	 */
	public ArticleVendu(String nom_article, String description, LocalDateTime date_debut_encheres,
			LocalDateTime date_fin_encheres, int prix_initial, String image, int no_utilisateur, int no_categorie,
			int no_retrait) {
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.image = image;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.no_retrait = no_retrait;
	}
	
	/**
	 * Sans id , prix de vente et photo
	 * 
	 */
	public ArticleVendu(String nom_article, String description, LocalDateTime date_debut_encheres,
			LocalDateTime date_fin_encheres, int prix_initial, int no_utilisateur, int no_categorie,
			int no_retrait) {
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.no_retrait = no_retrait;
	}

	/**
	 * All 
	 * 
	 */
	public ArticleVendu(int no_article, String nom_article, String description, LocalDateTime date_debut_encheres,
			LocalDateTime date_fin_encheres, int prix_initial, int prix_vente, String image, int no_utilisateur, int no_categorie,
			int no_retrait) {
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.image = image;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.no_retrait = no_retrait;
	}

	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public String getNom_article() {
		return nom_article;
	}

	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate_debut_encheres() {
		return date_debut_encheres;
	}

	public void setDate_debut_encheres(LocalDateTime date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}

	public LocalDateTime getDate_fin_encheres() {
		return date_fin_encheres;
	}

	public void setDate_fin_encheres(LocalDateTime date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}

	public int getPrix_initial() {
		return prix_initial;
	}

	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}

	public int getPrix_vente() {
		return prix_vente;
	}

	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}

	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public int getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}

	public int getNo_retrait() {
		return no_retrait;
	}

	public void setNo_retrait(int no_retrait) {
		this.no_retrait = no_retrait;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ArticleVendu [no_article=" + no_article + ", nom_article=" + nom_article + ", description="
				+ description + ", date_debut_encheres=" + date_debut_encheres + ", date_fin_encheres="
				+ date_fin_encheres + ", prix_initial=" + prix_initial + ", prix_vente=" + prix_vente
				+ ", no_utilisateur=" + no_utilisateur + ", no_categorie=" + no_categorie + ", no_retrait=" + no_retrait
				+ "]";
	}
	

}
