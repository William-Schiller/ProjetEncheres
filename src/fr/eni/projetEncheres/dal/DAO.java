package fr.eni.projetEncheres.dal;

public interface DAO<T> {
	
	public void insert(T t) throws DALException;
	
	
}
