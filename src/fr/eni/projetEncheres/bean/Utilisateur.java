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
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, int code_postal, String ville, String mot_de_passe, byte administrateur) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = 100;
		this.administrateur = administrateur;
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email,
			String rue, int code_postal, String ville, String mot_de_passe, byte administrateur) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = 100;
		this.administrateur = administrateur;
	}
	
	
	public int getNo_utlisateur() {
		return no_utlisateur;
	}

	public void setNo_utlisateur(int no_utlisateur) {
		this.no_utlisateur = no_utlisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(int code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public byte getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(byte administrateur) {
		this.administrateur = 0;
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [no_utlisateur=" + no_utlisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", code_postal="
				+ code_postal + ", ville=" + ville + ", mot_de_passe=" + mot_de_passe + ", credit=" + credit
				+ ", administrateur=" + administrateur + "]";
	}

}
