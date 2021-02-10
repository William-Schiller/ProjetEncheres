package fr.eni.projetEncheres.dal;

import fr.eni.projetEncheres.bean.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	
	public Utilisateur selectByID(int id) throws DALException;
	
	public Utilisateur selectByPseudo(String pseudo, String mot_de_passe) throws DALException;

	

}
