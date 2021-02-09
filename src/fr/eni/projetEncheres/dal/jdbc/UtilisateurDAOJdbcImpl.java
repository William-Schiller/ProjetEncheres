package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	@Override
	public void insert(Utilisateur u) throws DALException {
		// TODO DR (pensé à recuperer le clé auto générer
		
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Utilisateur u) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur selectByID(int id) throws DALException {
		// TODO Aurelien
		return null;
	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) throws DALException {
		Utilisateur user = null;
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur"
					+ " FROM Utilisateurs WHERE pseudo=?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, pseudo);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				user = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), 
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getInt("code_postal"), 
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getByte("administrateur"));
			}
			
		} catch (SQLException e) {
			throw new DALException("Couche DAL - ");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
		return user;
	}

}
