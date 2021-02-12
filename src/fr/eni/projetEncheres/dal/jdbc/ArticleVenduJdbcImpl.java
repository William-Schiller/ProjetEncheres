package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;

public class ArticleVenduJdbcImpl implements DAO<ArticleVendu> {

	@Override
	public void insert(ArticleVendu a) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, no_utilisateur, no_categorie, no_retrait) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, a.getNom_article());
			stmt.setString(2, a.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(a.getDate_debut_encheres())); // TODO Vérif
			stmt.setTimestamp(4, Timestamp.valueOf(a.getDate_fin_encheres())); // TODO Vérif
			stmt.setInt(5, a.getPrix_initial());
			stmt.setInt(6, a.getNo_utilisateur());
			stmt.setInt(7, a.getNo_categorie());
			stmt.setInt(8, a.getNo_retrait());
			
			stmt.executeUpdate();
			
			 ResultSet rs = stmt.getGeneratedKeys();
		      
		      if(rs.next()) {
		    	  a.setNo_article(rs.getInt(1));
		      }  
			
		} catch (SQLException e) {
			throw new DALException("methode insert : " + a.toString());
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		List<ArticleVendu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT (no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait) FROM ARTICLES_VENDUS";
			
			stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), 
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
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
	public ArticleVendu selectByID(int id) throws DALException {
		ArticleVendu article = null;
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				article = new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), 
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						);
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectById");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		return article;
	}

	@Override
	public void update(ArticleVendu a) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
					
			String sql = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?,"
					+ " prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ?, no_retrait = ? WHERE no_article=?";	

			
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, a.getNom_article());
			stmt.setString(2, a.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(a.getDate_debut_encheres())); // TODO Vérif
			stmt.setTimestamp(4, Timestamp.valueOf(a.getDate_fin_encheres())); // TODO Vérif
			stmt.setInt(5, a.getPrix_initial());
			stmt.setInt(6, a.getPrix_vente());
			stmt.setInt(7, a.getNo_utilisateur());
			stmt.setInt(8, a.getNo_categorie());
			stmt.setInt(9, a.getNo_retrait());
			
			stmt.setInt(10, a.getNo_article());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
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
			
			String sql = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			throw new DALException("Echec method delete()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}

}