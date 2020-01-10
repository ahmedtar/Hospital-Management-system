package daoImpl;

import java.sql.*;
import java.util.*;

import dao.MedecinDAO;
import model.Medecin;

public class MedecinDAOimpl implements  MedecinDAO {
	
	
	public int addMedecin(Medecin mdc){
		ConnectionDB con=null;
		try {
			con = new ConnectionDB();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement stm = null;
		
		int r=0;
		
			try {
				stm = con.getCon().prepareStatement("insert into medecin"
						+ " (nom, prenom, cne, sexe, age, numTel, adresse, specialisation, enservice)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
				stm.setString(1, mdc.getNom()); 
				stm.setString(2, mdc.getPrenom());
				stm.setString(3,mdc.getCne());
				stm.setString(4, mdc.getSexe());
				stm.setInt(5,mdc.getAge());
				stm.setString(6,mdc.getNumTel());
				stm.setString(7, mdc.getAdresse());
				stm.setString(8, mdc.getSpecialisation());
				stm.setBoolean(9, mdc.isEnservice());
				r=stm.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				con.close();
				close(stm,null);
			}
			System.out.println("addition was successful");
			return r;
	}

	
	
	public int updateMedecin(Medecin mdc){
		ConnectionDB con=null;
		try {
			con = new ConnectionDB();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement stm = null;
        int r=0;
		try {
			stm = con.getCon().prepareStatement("update medecin"
					+ " set nom=?, prenom=?, cne=?, sexe=?,age=?,numTel=?,"
					+ "adresse=?,specialisation=?,enservice=?"
					+ " where id=?");
			stm.setString(1, mdc.getNom());
			stm.setString(2, mdc.getPrenom());
			stm.setString(3,mdc.getCne());
			stm.setString(4, mdc.getSexe());
			stm.setInt(5,mdc.getAge());
			stm.setString(6,mdc.getNumTel());
			stm.setString(7, mdc.getAdresse());
			stm.setString(8, mdc.getSpecialisation());
			stm.setBoolean(9, mdc.isEnservice());
			stm.setInt(10, mdc.getId());
			r=stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
			close(stm,null);
		}
		System.out.println("you update DB ");
		return r;
	}
	
	
	
	
	
	public int deleteMedecin(Medecin mdc){
		ConnectionDB con=null;
		try {
			con = new ConnectionDB();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement stm = null;
        int r=0;
		try {
			stm = con.getCon().prepareStatement("delete from medecin where id=?");
			stm.setInt(1, mdc.getId());
			r=stm.executeUpdate();			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con.close();
			close(stm,null);
		}
		System.out.println("you delete a medecin");
		return r;
	}
	
	
	
	
	
	
	
	

	
	
	public List<Medecin> getAllMdcs(){
		ConnectionDB con=null;
		try {
			con = new ConnectionDB();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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
	



public List<Medecin> searchMdc(String str) {
		ConnectionDB con=null;
		try {
			con = new ConnectionDB();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Medecin> tmplistmdc=new ArrayList<>();
		PreparedStatement stm=null;
		ResultSet rslt=null;
		
			try {
				str+='%';
				stm=con.getCon().prepareStatement("select * from medecin where"
						+ " cne like ? "
						+ " or nom like ?"
						+ " or prenom like ?"
						+ " or specialisation like ?");
				stm.setString(1, str);
				stm.setString(2, str);
				stm.setString(3, str);
				stm.setString(4, str);
				
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
	ConnectionDB con=null;
	try {
		con = new ConnectionDB();
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
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
			boolean enservice=rslt.getBoolean("enservice");
			tmpmdc= new Medecin(id,nom, prenom, cne, sexe, age, numTel, adresse, specialisation, enservice);
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
