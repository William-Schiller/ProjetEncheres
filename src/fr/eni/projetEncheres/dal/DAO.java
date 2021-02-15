package fr.eni.projetEncheres.dal;

import java.util.List;

import fr.eni.projetEncheres.bean.Categorie;


public interface DAO<T> {
	
	public void insert(T t) throws DALException;
	
	public List<T> selectAll() throws DALException;
	
	public T selectByID(int id) throws DALException;
	
	public void update(T t) throws DALException;
	
	public void delete(int id) throws DALException;

	
   
	}
		
		
	
		
	

