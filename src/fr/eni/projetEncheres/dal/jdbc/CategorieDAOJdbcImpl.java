package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;

public class CategorieDAOJdbcImpl implements DAO<Categorie> {
	
	

	@Override
	public void insert(Categorie t) throws DALException {
	
		final String INSERT_NOM = "INSERT INTO CATEGORIE( libelle)" + " VALUES (?)";
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
		    pstmt = con.prepareStatement(INSERT_NOM, Statement.RETURN_GENERATED_KEYS);
		    
		    pstmt.setString(1, t.getLibelle());
		    System.out.println(pstmt.toString());
		      
		      pstmt.executeUpdate();
		      
		      ResultSet rs = pstmt.getGeneratedKeys();
		      
		      if(rs.next()) {
		    	  t.setNo_categorie(rs.getInt(1));
		      }  
		    
			
		} catch (SQLException e) {
			throw new DALException("Probleme - ajouterCategorie - " + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
	

		
	}

	@Override
	public List<Categorie> selectAll() throws DALException {
		
		
		
		
		
		
		
		
		return null;
	}

	@Override
	public void update(Categorie t) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	

	
}
