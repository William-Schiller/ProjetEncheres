package fr.eni.projetEncheres.dal.jdbc;

import java.util.List;

import fr.eni.projetEncheres.bean.Retrait;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;

public class RetraitDAOJdbcImpl implements DAO<Retrait>{

	@Override
	public void insert(Retrait t) throws DALException {
		final String INSERER ="insert into RETRAITS(rue, code_postal, ville,) VALUES (?, ?, ?);";
	    
	}

	@Override
	public List<Retrait> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retrait selectByID(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Retrait t) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
