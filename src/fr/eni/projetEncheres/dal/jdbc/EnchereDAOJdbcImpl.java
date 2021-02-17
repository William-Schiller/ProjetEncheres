package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bean.Enchere;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;
import fr.eni.projetEncheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	public void insert(Enchere e) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) "
					+ "VALUES(?, ?, ?, ?)";
			
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setTimestamp(1, Timestamp.valueOf(e.getDate_enchere())); // TODO Vérif
			stmt.setInt(2, e.getMontant_enchere());
			stmt.setInt(3, e.getNo_article());
			stmt.setInt(4, e.getNo_utilisateur());
			
			stmt.executeUpdate();
			
			 ResultSet rs = stmt.getGeneratedKeys();
		      
		      if(rs.next()) {
		    	  e.setNo_enchere(rs.getInt(1));
		      }  
			
		} catch (SQLException ex) {
			throw new DALException("methode insert : " + ex.toString());
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		List<Enchere> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT (no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur) FROM ENCHERES";
			
			stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Enchere(
						rs.getInt("no_enchere"), rs.getTimestamp("date_enchere").toLocalDateTime(), 
						rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur")
						));
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectAll");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		return list;
	}

	@Override
	public Enchere selectByID(int id) throws DALException {
		Enchere enchere = null;
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT * FROM ENCHERES WHERE no_enchere = ?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				enchere = new Enchere(
						rs.getInt("no_enchere"), rs.getTimestamp("date_enchere").toLocalDateTime(), 
						rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur")
						);
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectById");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		return enchere;
	}

	@Override
	public void update(Enchere e) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
					
			String sql = "UPDATE ENCHERES SET date_enchere = ?, montant_enchere = ?, no_article = ?, no_utilisateur = ?, "
					+ "WHERE no_enchere=?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setTimestamp(1, Timestamp.valueOf(e.getDate_enchere())); // TODO Vérif
			stmt.setInt(2, e.getMontant_enchere());
			stmt.setInt(3, e.getNo_article());
			stmt.setInt(4, e.getNo_utilisateur());

			stmt.setInt(5, e.getNo_enchere());
			
			stmt.executeUpdate();
			
		} catch (SQLException ex) {
			throw new DALException("Echec method update()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}

	@Override
	public void delete(int id) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			
			String sql = "DELETE FROM ENCHERES WHERE no_enchere = ?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			throw new DALException("Echec method delete()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}
	
	@Override
	public Enchere recupEnchereMax(int id_article) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		Enchere enchere = null;

		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT TOP 1 * FROM ENCHERES WHERE no_article = ? ORDER BY montant_enchere DESC";
			
			System.out.println("2");
			System.out.println("id :" + id_article);
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id_article);
			ResultSet rs = stmt.executeQuery();
			
			System.out.println("3");
			
			if (rs.next()) {
				enchere = new Enchere(rs.getInt("no_enchere"), rs.getTimestamp("date_enchere").toLocalDateTime(), 
						rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur"));
				System.out.println("4");
			}
			System.out.println("5");

		} catch (SQLException e) {
			throw new DALException("echec methode recupEnchereMax");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		} 
		System.out.println("6 :" + enchere);
		return enchere;
		
	}

}
