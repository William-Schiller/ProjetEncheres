package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	
	/**rose dorleans
	 * 
	 */
	@Override
	public void insert(Utilisateur u) throws DALException {


		 
		final String INSERT_INFO =
				"INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";


		PreparedStatement pstmt = null;
		Connection con = null;

	    try {
	    	con = ConnectionProvider.getConnection();
	      pstmt = con.prepareStatement(INSERT_INFO, Statement.RETURN_GENERATED_KEYS);
	      
	      pstmt.setString(1, u.getPseudo());
	      pstmt.setString(2, u.getNom());
	      pstmt.setString(3, u.getPrenom());
	      pstmt.setString(4, u.getEmail());
	      pstmt.setString(5, u.getTelephone());   
	      pstmt.setString(6, u.getRue());
	      pstmt.setInt(7, u.getCode_postal());
	      pstmt.setString(8, u.getVille());
	      pstmt.setString(9, u.getMot_de_passe());
	      pstmt.setInt(10, 100);
	      pstmt.setByte(11, (byte)0);
	      

	      
	      pstmt.executeUpdate();
	      
	      ResultSet rs = pstmt.getGeneratedKeys();
	      
	      if(rs.next()) {
	    	  u.setNo_utlisateur(rs.getInt(1));
	      }      
	    }
	    catch(SQLException e){
	    	throw new DALException ("Erreur methode insert : " + u.toString());
	    } finally {
	    	ConnectionProvider.connectionClosed(con, pstmt);
	    }
	}

	/**
	 * @author : ws
	 */
	@Override
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> list = new ArrayList<>();
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT * FROM Utilisateurs";
			
			stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Utilisateur(
						rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), 
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getInt("code_postal"), 
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getByte("administrateur")
						));
			}
	
		} catch (SQLException e) {
			throw new DALException("Echec method selectAll()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		return list;
	}

	/**
	 * @author : ws
	 */
	@Override
	public void update(Utilisateur u) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
					
			String sql = "UPDATE Utilisateurs SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, "
					+ "rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ? WHERE no_utilisateur=?";	
			
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, u.getPseudo());
			stmt.setString(2, u.getNom());
			stmt.setString(3, u.getPrenom());
			stmt.setString(4, u.getEmail());
			stmt.setString(5, u.getTelephone());
			stmt.setString(6, u.getRue());
			stmt.setInt(7, u.getCode_postal());
			stmt.setString(8, u.getVille());
			stmt.setString(9, u.getMot_de_passe());
			stmt.setInt(10, u.getCredit());
			
			stmt.setInt(11, u.getNo_utlisateur());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException("Echec method update()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}
	
	/**
	 * @author : ws
	 */
	@Override
	public void delete(int id) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			
			String sql = "DELETE FROM Utilisateurs WHERE no_utilisateur = ?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			throw new DALException("Echec method delete()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}
	
	
	/**aurelien suel
	 * 
	 */
	@Override
	public Utilisateur selectByID(int id) throws DALException {
     	Connection cnx=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Utilisateur utilisateur = null;
	    try {
			cnx = ConnectionProvider.getConnection();
			
			String sql = "select pseudo,nom,prenom,email,telephone,rue,code_postal,ville,credit from UTILISATEURS where no_utilisateur = ?;";
			stmt=cnx.prepareStatement(sql);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
		
			if (rs.next()){
			
				utilisateur = new Utilisateur();
				
				utilisateur.setNo_utlisateur(id);
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(Integer.parseInt(rs.getString("code_postal")));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setCredit(rs.getInt("credit"));

			}
		}catch (SQLException e){
			throw new DALException ("Probleme - rechercherUtilisateur - " + e.getMessage());
		}finally{	
			ConnectionProvider.connectionClosed(cnx, stmt);	
		}
		return utilisateur;
	}
	
	
	/**
	 * @author : sw 
	 */
	@Override
	public Utilisateur selectByPseudo(String pseudo, String mot_de_passe) throws DALException {
		Utilisateur user = null;
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur"
					+ " FROM Utilisateurs WHERE (pseudo=? OR email=?) AND mot_de_passe=?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, pseudo);
			stmt.setString(2, pseudo);
			stmt.setString(3, mot_de_passe);
			
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
