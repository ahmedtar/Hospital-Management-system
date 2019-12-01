package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ConnectionDB {
	
	private Connection con;
	private PreparedStatement stmt;
	
	public ConnectionDB() {
		
		try{
			String url="jdbc:mysql://localhost:3306/hopital";
			String user="root";
			String pass="";
			con=DriverManager.getConnection(url,user,pass);
		//	stmt=con.createStatement();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("erreur!!!");
		}
	}
	
	//inserer une personne ...c'est private car va etre utilisé juste pour l'insertion de medecin et patient
/*	private int inserer(Personne p) throws SQLException {
		Statement stmt=con.createStatement();
		String req="Insert into personne values ('"
		+p.getNom()+"','"+p.getPrenom()+"','"+p.getSexe()+"','"+p.getAge()+"','"+p.getNumTel()+"','"+p.getAdresse()+"')";   
		
		int r=stmt.executeUpdate(req);
		con.close();
		
		return r;
		
	}*/
	public int insere(Departement d)throws SQLException{
		//Statement stmt=con.createStatement();
		//String req="insert into departement values ('"+d.getId()+"','"+d.getNom()+"')";
		String sql="insert into departement values ( ? , ? )";
		stmt=con.prepareStatement(sql);
		
		stmt.setInt(1,d.getId());
		stmt.setString(2,d.getNom());
		
		int r=stmt.executeUpdate();
		
		System.out.println("done");
		return r;
	}
	
	
	
	public int inserer(Patient m) throws SQLException {
		String sql="Select id from medecin where nom='"+m.getMedecin().getNom()+"'";
		ResultSet rSet=stmt.executeQuery(sql);
		rSet.next();
		int id=rSet.getInt("id");
		String req="INSERT INTO patient VALUES ('"+m.getId()+"','"+1+"','"+m.getNom()+"','"+m.getPrenom()+"','"+m.getSexe()+"','"+m.getAge()+"','"+m.getNumTel()+"','"+m.getAdresse()+"','"+m.getMaladie()+"','"+m.getDateEntree()+"','"+m.getDateSortie()+"','"+id+"')";
		int r=stmt.executeUpdate(req);
		
		return r;
		
	}
	
	

	public int inserer(Medecin m) throws SQLException { 
		//getting the id of the doctor's departement
		String sql="Select id from departement where nom='"+m.getDept()+"'";
		ResultSet rSet=stmt.executeQuery(sql);
		rSet.next();
		int id=rSet.getInt("id");
		//inserer le medecin
		String req="insert into medecin values ('"+m.getId()+"','"+m.getNom()+"','"+m.getPrenom()+"','"+m.getSexe()+"','"+m.getAge()+"','"+m.getNumTel()+"','"+m.getAdresse()+"','"+m.getSpecialisation()+"','"+id+"')";
		int r=stmt.executeUpdate(req);
		return r;
		
	}
	
	
	
	public void close() throws SQLException {
		this.con.close();
	}


}
