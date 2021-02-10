package fr.eni.projetEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	private static DataSource dataSource;
	
	static {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible d'accèder à la base de données");
		}		
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public static void connectionClosed(Connection con) throws DALException {
		try {
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new DALException("Erreur fermeture connexion");
		}	
	}
	
	public static void connectionClosed(Connection con, PreparedStatement stmt) throws DALException {
		try {
			if(stmt != null) {
				stmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new DALException("Erreur fermeture connexion");
		}
	}
	
}