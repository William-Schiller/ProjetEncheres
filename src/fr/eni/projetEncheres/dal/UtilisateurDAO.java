package fr.eni.projetEncheres.dal;

import fr.eni.projetEncheres.bean.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	
	public Utilisateur select(int id) throws DALException;

}
