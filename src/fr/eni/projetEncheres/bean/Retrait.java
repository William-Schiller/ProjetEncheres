package fr.eni.projetEncheres.bean;

import java.io.Serializable;

public class Retrait implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String rue;
	private int  code_postale;
	private String ville;
	
	
	public Retrait() {
		}
	
	public Retrait(String rue , int code_postale , String ville) {
		this.rue = rue;
		this.code_postale = code_postale;
		this.ville = ville;
		}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCode_postale() {
		return code_postale;
	}

	public void setCode_postale(int code_postale) {
		this.code_postale = code_postale;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", code_postale=" + code_postale + ", ville=" + ville + "]";
	}

	
}
