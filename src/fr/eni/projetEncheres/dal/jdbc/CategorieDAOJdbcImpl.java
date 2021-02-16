package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bean.Categorie;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;

public class CategorieDAOJdbcImpl implements DAO<Categorie> {
	
	
    /**
     * aurelien
     */
	@Override
	public void insert(Categorie t) throws DALException {
	
		final String INSERT_NOM = "INSERT INTO CATEGORIE(libelle)" + " VALUES (?)";
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
    
	/**
	 * aurelien
	 */
	@Override
	public List<Categorie> selectAll() throws DALException  {
		
		List<Categorie> listcategorie = new ArrayList<>();
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT * FROM CATEGORIES";
			
			stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				listcategorie.add(new Categorie(
						rs.getInt("no_categorie"), rs.getString("libelle")
						));
			}
		} catch (SQLException e) {
			throw new DALException("Echec method selectAll()");
		} finally {
		
			try {
				if(stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listcategorie;
		
		
	}
    
	/**
	 * aurelien
	 */
	@Override
	public void update(Categorie t) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
            String sql = "UPDATE Utilisateurs SET libelle = ?";
			
            stmt.setString(1, t.getLibelle());
			
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException("Echec method update()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}
	
		
	
    /**
     * aurelien
     */
	@Override
	public void delete(int id) throws DALException {
		
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			
			String sql = "DELETE FROM Categorie WHERE no_categorie = ?";
		
            stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Echec method delete()");
		
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	
	}

	/**
	 * aurelien
	 */
	@Override
	public Categorie selectByID(int id) throws DALException {
		Connection cnx=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Categorie categorie = null;
	    try {
			cnx = ConnectionProvider.getConnection();
			
			String sql = "select libelle from CATEGORIE where no_categorie = ?;";
			stmt=cnx.prepareStatement(sql);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
		
			if (rs.next()){
				categorie = new Categorie();
				
				categorie.setLibelle(rs.getString("libelle"));
		
			}
		}catch (SQLException e){
			throw new DALException ("Probleme - rechercherUtilisateur - " + e.getMessage());
		}finally{	
			ConnectionProvider.connectionClosed(cnx, stmt);	
		}
		return categorie;
	
	
	}

	
 }	

