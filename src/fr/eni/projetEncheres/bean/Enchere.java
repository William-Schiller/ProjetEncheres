package fr.eni.projetEncheres.bean;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Enchere implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int no_enchere;
	private LocalDateTime date_enchere;
	private int montant_enchere;
	private int no_article;
	private int no_utilisateur;
	
	public Enchere() {
	}
	
	public Enchere(int no_enchere, LocalDateTime date_enchere, int montant_enchere, int no_article,
			int no_utilisateur) {
		this.no_enchere = no_enchere;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.no_article = no_article;
		this.no_utilisateur = no_utilisateur;
	}

	public Enchere(LocalDateTime date_enchere, int montant_enchere, int no_article,
			int no_utilisateur) {
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.no_article = no_article;
		this.no_utilisateur = no_utilisateur;
	}

	public int getNo_enchere() {
		return no_enchere;
	}

	public void setNo_enchere(int no_enchere) {
		this.no_enchere = no_enchere;
	}

	public LocalDateTime getDate_enchere() {
		return date_enchere;
	}

	public void setDate_enchere(LocalDateTime date_enchere) {
		this.date_enchere = date_enchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	@Override
	public String toString() {
		return "Enchere [no_enchere=" + no_enchere + ", date_enchere=" + date_enchere + ", montant_enchere="
				+ montant_enchere + ", no_article=" + no_article + ", no_utilisateur=" + no_utilisateur + "]";
	}
	
}
