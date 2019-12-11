package daoImpl;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	
	
	
	@Override
	public int updateUtilisateur(Utilisateur utilisateur) throws SQLException {
		ConnectionDB con=new ConnectionDB();
		PreparedStatement myStmt = null;
		int r=0;

		try {
			// prepared statement
			myStmt = con.getCon().prepareStatement("update utilisateur"
					+ " set login=?, nom=?, prenom=?, password=?, estAdmin=?, estActif=? where id=?");
			
			
			myStmt.setString(1, utilisateur.getLogin());
			myStmt.setString(2, utilisateur.getNom());
			myStmt.setString(3,utilisateur.getPrenom());
			myStmt.setString(4,utilisateur.getPassword());
			myStmt.setBoolean(5,utilisateur.EstAdmin());
			myStmt.setBoolean(6,utilisateur.EstActif());
			myStmt.setInt(7, utilisateur.getId());
			
			
			
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
	public List<Utilisateur> getAllUtilisateurs() throws Exception {
		ConnectionDB con=new ConnectionDB();
		List<Utilisateur> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = con.getCon().createStatement();
			myRs = myStmt.executeQuery("select * from utilisateur order by nom");
			
			while (myRs.next()) {
				Utilisateur tempUtilisateur = convertRowToUtilisateur(myRs);
				list.add(tempUtilisateur);
			}

					
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		finally {
			con.close();
		}
		return list;
	}
	
	
	
	
	
	private Utilisateur  convertRowToUtilisateur(ResultSet myRs) throws Exception {
		
		int id = myRs.getInt("id");
		String nom = myRs.getString("nom");
		String prenom = myRs.getString("prenom");
		String login=myRs.getString("login");
		String password=myRs.getString("password");
		boolean estAdmin=myRs.getBoolean("estAdmin");
		
		boolean estActif=myRs.getBoolean("estActif");
		Utilisateur u = new Utilisateur(id, login, nom, prenom, password, estAdmin, estActif);
		
		return u;

	 }


}
