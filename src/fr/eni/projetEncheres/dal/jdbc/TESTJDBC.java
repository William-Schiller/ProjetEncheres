package fr.eni.projetEncheres.dal.jdbc;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.DAOFactory;

public class TESTJDBC {
	public static void main(String[] args) {
		
		Categorie c = new Categorie (1, "cacao");
		
		DAO<Categorie> cDAO = DAOFactory.getCategorieDAO();
		try {
			cDAO.insert(c);
		}catch (DALException e) {
			e.printStackTrace();
		}
		
		List<Categorie> list = new ArrayList<>();
		try {
			list = cDAO.selectAll();
			
		}catch (Exception e) {
		
			// TODO: handle exception
		e.printStackTrace();
	}
		for (Categorie categorie : list) {
			System.out.println(categorie.toString());
		}
	}
}
