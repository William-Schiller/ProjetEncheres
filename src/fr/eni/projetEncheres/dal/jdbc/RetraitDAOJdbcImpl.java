package fr.eni.projetEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import fr.eni.projetEncheres.bean.Retrait;
import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.dal.ConnectionProvider;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAO;

public class RetraitDAOJdbcImpl implements DAO<Retrait>{
    
	/**
	 * Aurelien
	 */
	@Override
	public void insert(Retrait t) throws DALException {

		final String INSERER = "insert into RETRAITS(rue, code_postal, ville,) VALUES (?, ?, ?);";
		PreparedStatement pstmt = null;
		Connection con = null;
		try {	
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(INSERER, Statement.RETURN_GENERATED_KEYS);


			pstmt.setString(1, t.getRue());
			pstmt.setInt(2, t.getCode_postale());
			pstmt.setString(8, t.getVille());

			pstmt.executeUpdate();


			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				t.setNo_retrait(rs.getInt(1));
			}

		}
		catch(SQLException e){
			throw new DALException ("Erreur methode insert : " + t.toString());
		} finally {
			ConnectionProvider.connectionClosed(con, pstmt);
		}

	}


    /**
     * Aurelien
     */
	@Override
	public List<Retrait> selectAll() throws DALException {

		List<Retrait> retrait = new ArrayList<>();
		PreparedStatement stmt = null;
		Connection con = null;

		try {

			con = ConnectionProvider.getConnection();

			String sql = "SELECT * FROM RETRAIT";

			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				retrait.add(new Retrait(
						rs.getInt("no_retrait")	,rs.getString("rue"),rs.getInt("code_postale"), rs.getString("ville")));

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
		return retrait;


	}
    
	/**
     * Aurelien
     */
	@Override
		public Retrait selectByID(int id) throws DALException {
		Connection cnx=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Retrait retrait  = null;

		try {
			
			String sql = "select pseudo,nom,prenom,email,telephone,rue,code_postal,ville from UTILISATEURS where no_utilisateur = ?;";
			stmt=cnx.prepareStatement(sql);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
		
			if (rs.next()){
			
				retrait = new Retrait();
				
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postale(Integer.parseInt(rs.getString("code_postal")));
				retrait.setVille(rs.getString("ville"));

			}

		} catch (SQLException e){

			throw new DALException ("Probleme - retrait - " + e.getMessage());
		}finally{	
			ConnectionProvider.connectionClosed(cnx, stmt);	
		}
		return retrait;

	}

	/**
	 * aurelien
	 */
	@Override
	public void update(Retrait t) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;

		try {

			con = ConnectionProvider.getConnection();
			String sql = "UPDATE Retrait SET (rue, code_postal, ville,) VALUES (?, ?, ?);";

			stmt.setString(1, t.getRue());
			stmt.setInt(2, t.getCode_postale());
			stmt.setString(3, t.getVille());

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
    			
    			String sql = "DELETE FROM RETRAIT WHERE no_retrait = ?";
    			
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


