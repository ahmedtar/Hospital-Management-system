package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UtilisateurDao;
import model.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

	@Override
	public int AddUtilisateur(Utilisateur u)  {
		ConnectionDB con=new ConnectionDB();
		PreparedStatement myStmt = null;
		int r=0;
		try {
			// prepared statement
			myStmt = con.getCon().prepareStatement("insert into utilisateur"
					+ " (login, nom, prenom, password,estAdmin, estActif )"
					+ " values ( ?, ?, ?, ?, ? ,?)");
			
			
			myStmt.setString(1, u.getLogin());
			myStmt.setString(2, u.getNom());
			myStmt.setString(3,u.getPrenom());
			myStmt.setString(4, u.getPassword());
			myStmt.setBoolean(5, u.EstAdmin());
			myStmt.setBoolean(6,u.EstActif());
			
			
			
			r=myStmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return r;

	}

	@Override
	public int deleteUtilisateur(int id) {
		
			ConnectionDB con=new ConnectionDB();
			PreparedStatement myStmt = null;
			int r=0;
			try {
				// prepare statement
				myStmt = con.getCon().prepareStatement("delete from utilisateur where id=?");
				
				// set param
				myStmt.setInt(1, id);
				
				// execute SQL
				r=myStmt.executeUpdate();			
			}
			 catch (SQLException e) {
					e.printStackTrace();
				}
			finally {
				con.close();
			}
		return r;

	}

	@Override
	public int login(String login,String password) {
		
		ConnectionDB myCon=new ConnectionDB();
		int r=0;
		ResultSet rsl=null;
		try {
			PreparedStatement myStmt=myCon.getCon().prepareStatement("SELECT * FROM utilisateur where login = ? and password = ? ");
			myStmt.setString(1, login);
			myStmt.setString(2, password);
			rsl=myStmt.executeQuery();
			if(!rsl.next()) r=-1;
			else r=1;
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		finally {
			myCon.close();
		}
		
		return r;
	}

}
