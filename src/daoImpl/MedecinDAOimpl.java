package daoImpl;

import java.sql.*;
import java.util.*;

import dao.MedecinDAO;
import model.Medecin;

public class MedecinDAOimpl implements  MedecinDAO {
	
	
	public void addMedecin(Medecin mdc){
		ConnectionDB con=new ConnectionDB();
		PreparedStatement stm = null;
			try {
				stm = con.getCon().prepareStatement("insert into medecin"
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
				con.close();
				close(stm,null);
			}
			System.out.println("addition was successful");
	}

	
	
	public void updateMedecin(Medecin mdc,Medecin mdcmodifier){
		ConnectionDB con=new ConnectionDB();
		PreparedStatement stm = null;

		try {
			stm = con.getCon().prepareStatement("update medecin"
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
			con.close();
			close(stm,null);
		}
		System.out.println("you update DB ");
	}

	
	
	public List<Medecin> getAllMdcs(){
		ConnectionDB con=new ConnectionDB();
		List<Medecin> tmplistmdc=new ArrayList<>();
		Statement stm=null;
		ResultSet rslt=null;
		try {
			stm=con.getCon().createStatement();
			rslt=stm.executeQuery("select * from medecin");
			while(rslt.next()) {
				Medecin tmpmdc=convertRowToMdc(rslt);
				tmplistmdc.add(tmpmdc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
			close(stm,rslt);
		}
		return tmplistmdc;
	}
	

public List<Medecin> searhMdc(String colomn,String str) {
		ConnectionDB con=new ConnectionDB();
		List<Medecin> tmplistmdc=new ArrayList<>();
		PreparedStatement stm=null;
		ResultSet rslt=null;
		if(colomn=="nom" || colomn=="prenom" ||colomn=="cne" ||colomn=="specialisation") {
			try {
				str+='%';
				stm=con.getCon().prepareStatement("select * from medecin where "+colomn+" like ? ");
				stm.setString(1, str);
				rslt=stm.executeQuery();
				
				while(rslt.next()) {
					Medecin tmpmdc=convertRowToMdc(rslt);
					tmplistmdc.add(tmpmdc);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				con.close();
				close(stm,rslt);
			}
			
		}else {
			System.out.println("erreur de recherche");
		}
		
		return tmplistmdc;
	}


	
public List<Medecin> searhDepartementDeMdc(int d) {
		ConnectionDB con=new ConnectionDB();
		List<Medecin> tmplistmdc=new ArrayList<>();
		PreparedStatement stm=null;
		ResultSet rslt=null;
		try {
			stm=con.getCon().prepareStatement("select * from medecin where departement like ? ");
			stm.setInt(1, d);
			rslt=stm.executeQuery();
			
			while(rslt.next()) {
				Medecin tmpmdc=convertRowToMdc(rslt);
				tmplistmdc.add(tmpmdc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
			close(stm,rslt);
		}
		
		return tmplistmdc;
	}
	
public List<Medecin> searhInServiceMdcs(boolean m) {
	ConnectionDB con=new ConnectionDB();
	List<Medecin> tmplistmdc=new ArrayList<>();
	PreparedStatement stm=null;
	ResultSet rslt=null;
	try {
		stm=con.getCon().prepareStatement("select * from medecin where enservice like ? ");
		stm.setBoolean(1, m);
		rslt=stm.executeQuery();
		
		while(rslt.next()) {
			Medecin tmpmdc=convertRowToMdc(rslt);
			tmplistmdc.add(tmpmdc);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		con.close();
		close(stm,rslt);
	}
	
	return tmplistmdc;
}



	public void deleteMedecin(int id){
		ConnectionDB con=new ConnectionDB();
		PreparedStatement stm = null;

		try {
			stm = con.getCon().prepareStatement("delete from medecin where id=?");
			stm.setInt(1, id);
			stm.executeUpdate();			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
			close(stm,null);
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
	
	
	private static void close(Statement stm ,ResultSet rslt) {
			try {
				if(rslt!=null)rslt.close();
				if(stm!=null)stm.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
