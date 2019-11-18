package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ConnectionDB {
	
	private Connection con;
	
	public ConnectionDB() {
		
		try{
			String url="jdbc:mysql://localhost:3306/demo";
			String user="root";
			String pass="";
			con=DriverManager.getConnection(url,user,pass);
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("erreur!!!");
		}
	}
	
	//inserer une personne ...c'est private car va etre utilisé juste pour l'insertion de medecin et patient
	private int inserer(Personne p) throws SQLException {
		Statement stmt=con.createStatement();
		String req="Insert into personne values ('"
		+p.getNom()+"','"+p.getPrenom()+"','"+p.getSexe()+"','"+p.getAge()+"','"+p.getNumTel()+"','"+p.getAdresse()+"')";   
		
		int r=stmt.executeUpdate(req);
		con.close();
		
		return r;
		
	}
	
	public int inserer(Patient p) throws SQLException {
		inserer((Personne)p);
		Statement stmt=con.createStatement();
		String req="Insert into Patient"
				+ "(Chambre ,Maladie,dateEntree,dateSortie) values "
				+ "('"+p.getId()+"','"+/*p.getChambre().getId()+*/"','"+p.getMaladie()+"','"+p.getDateEntree()+"','"+p.getDateSortie()+"')";
		int r=stmt.executeUpdate(req);
		con.close();
		
		return r;
		
	}
	
	
	//we need to know how to make one to many relationship in phpmyadmin
	public int inserer(Medecin m) throws SQLException {
		inserer((Personne)m);
		Statement stmt=con.createStatement();
		String req="Insert into Patient"
				+ "values "
				+ "('"+m.getId()+"','"+m.getDept().getId()+"','"+m.getSpecialisation()+"','"+m.getPatients()+"')";
		int r=stmt.executeUpdate(req);
		con.close();
		
		return r;
		
	}


}
