package fr.eni.projetEncheres.bean;

import java.io.Serializable;

public class Utilisateur implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int no_utlisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private int code_postal;
	private String ville;
	private String mot_de_passe;
	private int credit;
	private byte administrateur;

	
	public Utilisateur() {
	}

	public Utilisateur(int no_utlisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, int code_postal, String ville, String mot_de_passe, int credit, byte administrateur) {
		this.no_utlisateur = no_utlisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	
	
	
	
}
