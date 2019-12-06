package model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class MedecinDAO {
	private Connection con;
	
	public MedecinDAO(){
		//get DB properties 
		
		Properties props=new Properties();
		try {
			props.load(new FileInputStream("hospital.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("file hospital.properties not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String user=props.getProperty("user");
		String password=props.getProperty("password");
		String dburl=props.getProperty("dburl");
		
		//get DB connection
		
		try {
			con=DriverManager.getConnection(dburl,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("connection successful to "+dburl);
	}
	
	
	public void addMedecin(Medecin mdc){
		PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("insert into medecin"
						+ " (nom, prenom, cne, sexe, age, numTel, adresse, specialisation, departement, enservice)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
				stm.setString(1, mdc.getNom());
				stm.setString(2, mdc.getPrenom());
				stm.setString(3,mdc.getCne());
				stm.setString(4, mdc.getSexe());
				stm.setInt(5,mdc.getAge());
				stm.setString(6,mdc.getNumTel());
				stm.setString(7, mdc.getAdresse());
				stm.setString(8, mdc.getSpecialisation());
				stm.setInt(9, mdc.getDepartement());
				stm.setBoolean(10, mdc.isEnservice());
				stm.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(stm);
			}
			System.out.println("addition was successful");
	}

	
	
	public void updateMedecin(Medecin mdc,Medecin mdcmodifier){
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("update medecin"
					+ " set nom=?, prenom=?, cne=?, sexe=?,age=?,numTel=?,"
					+ "adresse=?,specialisation=?,departement=?,enservice=?"
					+ " where id=?");
			stm.setString(1, mdcmodifier.getNom());
			stm.setString(2, mdcmodifier.getPrenom());
			stm.setString(3,mdcmodifier.getCne());
			stm.setString(4, mdcmodifier.getSexe());
			stm.setInt(5,mdcmodifier.getAge());
			stm.setString(6,mdcmodifier.getNumTel());
			stm.setString(7, mdcmodifier.getAdresse());
			stm.setString(8, mdcmodifier.getSpecialisation());
			stm.setInt(9, mdcmodifier.getDepartement());
			stm.setBoolean(10, mdcmodifier.isEnservice());
			stm.setInt(11, mdc.getId());
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stm);
		}
		System.out.println("you update DB ");
	}

	
	
	public List<Medecin> getAllMdcs(){
		List<Medecin> tmplistmdc=new ArrayList<>();
		Statement stm=null;
		ResultSet rslt=null;
		try {
			stm=con.createStatement();
			rslt=stm.executeQuery("select * from medecin");
			while(rslt.next()) {
				Medecin tmpmdc=convertRowToMdc(rslt);
				tmplistmdc.add(tmpmdc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stm,rslt);
		}
		return tmplistmdc;
	}
	

public List<Medecin> searhMdc(String colomn,String str) {
		
		List<Medecin> tmplistmdc=new ArrayList<>();
		PreparedStatement stm=null;
		ResultSet rslt=null;
		if(colomn=="nom" || colomn=="prenom" ||colomn=="cne" ||colomn=="specialisation") {
			try {
				str+='%';
				stm=con.prepareStatement("select * from medecin where "+colomn+" like ? ");
				stm.setString(1, str);
				rslt=stm.executeQuery();
				
				while(rslt.next()) {
					Medecin tmpmdc=convertRowToMdc(rslt);
					tmplistmdc.add(tmpmdc);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(stm,rslt);
			}
			
		}else {
			System.out.println("erreur de recherche");
		}
		
		return tmplistmdc;
	}


	
public List<Medecin> searhDepartementDeMdc(int d) {
		
		List<Medecin> tmplistmdc=new ArrayList<>();
		PreparedStatement stm=null;
		ResultSet rslt=null;
		try {
			stm=con.prepareStatement("select * from medecin where departement like ? ");
			stm.setInt(1, d);
			rslt=stm.executeQuery();
			
			while(rslt.next()) {
				Medecin tmpmdc=convertRowToMdc(rslt);
				tmplistmdc.add(tmpmdc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stm,rslt);
		}
		
		return tmplistmdc;
	}
	
public List<Medecin> searhInServiceMdcs(boolean m) {
	
	List<Medecin> tmplistmdc=new ArrayList<>();
	PreparedStatement stm=null;
	ResultSet rslt=null;
	try {
		stm=con.prepareStatement("select * from medecin where enservice like ? ");
		stm.setBoolean(1, m);
		rslt=stm.executeQuery();
		
		while(rslt.next()) {
			Medecin tmpmdc=convertRowToMdc(rslt);
			tmplistmdc.add(tmpmdc);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(stm,rslt);
	}
	
	return tmplistmdc;
}



	public void deleteMedecin(int id){
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("delete from medecin where id=?");
			stm.setInt(1, id);
			stm.executeUpdate();			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stm);
		}
		System.out.println("you delete a medecin");
	}

	
	
	
	
	
	
	
	
	private Medecin convertRowToMdc(ResultSet rslt) {
		Medecin tmpmdc=null;
		try {
			
			int id=rslt.getInt("id");
			String nom=rslt.getString("nom");
			String prenom=rslt.getString("prenom");
			String cne=rslt.getString("cne");
			String sexe=rslt.getString("sexe");
			int age=rslt.getInt("age");
			String numTel=rslt.getString("numTel");
			String adresse=rslt.getString("adresse");
			String specialisation=rslt.getString("specialisation");
			int departement=rslt.getInt("departement");
			boolean enservice=rslt.getBoolean("enservice");
			tmpmdc= new Medecin(id,nom, prenom, cne, sexe, age, numTel, adresse, specialisation, departement, enservice);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tmpmdc;
	}
	
	
	private static void close(Connection con,Statement stm ,ResultSet rslt) {
			try {
				if(rslt!=null)rslt.close();
				if(stm!=null)stm.close();
				if(con!=null)con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	private void close(Statement stm,ResultSet rslt) {
		close(null,stm,rslt);
	}
	
	private void close(Statement stm) {
		close(null,stm,null);
	}

}
