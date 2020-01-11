package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import dao.LitDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Chambre;
import model.Lit;
import model.Patient;


public class LitDaoImpl implements LitDao {

	
	
	@Override
	public ObservableList<Lit> searchLit(int id) throws Exception {
		
		ConnectionDB con=new ConnectionDB();
		ObservableList<Lit> list = FXCollections.observableArrayList();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = con.getCon().prepareStatement("select * from lit where id like ?");
			
			myStmt.setInt(1,id);
			
			myRs = myStmt.executeQuery();
			
			myRs.next();
			
				Lit tempLit= convertRowToLit(myRs);
				list.add(tempLit);
			
			
			
			
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		finally {
			con.close();
		}
		return list;

	}
	
	
	@Override
	public ObservableList<Lit> getLitsEnService() throws Exception {
		
		ConnectionDB con=new ConnectionDB();
		ObservableList<Lit> list = FXCollections.observableArrayList();

//		Statement myStmt = null;
		PreparedStatement stm = null;
		ResultSet myRs = null;

		try {
			String sql = "select * from lit where enservice=?";
//			myStmt = con.getCon().createStatement();
			stm = con.getCon().prepareStatement(sql);
			
		    stm.setBoolean(1, true);
			
			myRs = stm.executeQuery();
			
			
			
			while(myRs.next()) {
				Lit tempLit= convertRowToLit(myRs);
				list.add(tempLit);
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
	
	@Override
	public ObservableList<Lit> getLitsHorsService() throws Exception {
		
		ConnectionDB con=new ConnectionDB();
		ObservableList<Lit> list = FXCollections.observableArrayList();

//		Statement myStmt = null;
		PreparedStatement stm = null;
		ResultSet myRs = null;

		try {
			String sql = "select * from lit where enservice=?";
//			myStmt = con.getCon().createStatement();
			stm = con.getCon().prepareStatement(sql);
			
		    stm.setBoolean(1, false);
			
			myRs = stm.executeQuery();
			
			
			
			while(myRs.next()) {
				Lit tempLit= convertRowToLit(myRs);
				list.add(tempLit);
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

	
	@Override
	public Lit searchLitById(int id) throws Exception {
		
		ConnectionDB con=new ConnectionDB();
		Lit lit = new Lit();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = con.getCon().prepareStatement("select * from lit where id=?");
			
			myStmt.setInt(1,id);
			
			myRs = myStmt.executeQuery();
			
			myRs.next();
			 
			
			lit = convertRowToLit(myRs);
			
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		finally {
			con.close();
		}
		return lit;

	}
	
	@Override
	public List<Lit> searchLitByChambre(int chambreId) throws Exception {
		
		ConnectionDB con=new ConnectionDB();
		List<Lit> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = con.getCon().prepareStatement("select * from lit where chambre=?");
			
			myStmt.setInt(1, chambreId);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Lit tempLit = convertRowToLit(myRs);
				list.add(tempLit);
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
	
	
    private Lit  convertRowToLit(ResultSet myRs) throws Exception {
	
	    
		PatientDaoImpl Pdao=new PatientDaoImpl();
		int id = myRs.getInt("id");
		System.out.println(id);
		boolean enService = myRs.getBoolean("enService");
		int chambreId=myRs.getInt("chambre");
		
		
//		Patient patient=Pdao.searchPatientByLit(id);
		ChambreDaoImpl dao=new ChambreDaoImpl();
	//	Chambre chambre=dao.searchChambre(chambreId);
		
		
		Lit lit=new Lit(id, enService , null);
		return lit;

	 }


    public void setEnService() throws Exception{
    	LitDaoImpl litDao = new LitDaoImpl();
    	PatientDaoImpl pDao = new PatientDaoImpl();
    	
    	List<Patient> patients = pDao.getAllPatients();
    	ObservableList<Integer> ids = FXCollections.observableArrayList();
    	for(int i=0 ; i<patients.size(); i++) {
    		
    	}
    	
    	ConnectionDB conn = new ConnectionDB();
    	String sql = "update lit set enservice=true";
    	String sql2 ="update lit set enservice=? where id in ? ";
    	
//    	String sql3 = "(select lit from patient where nom='lit')";
    	PreparedStatement stm = null,stm2 =null;
    	ResultSet r;
    	
    	try {
			stm = conn.getCon().prepareStatement(sql);
//			stm.setBoolean(1, false);
			stm.executeUpdate();
//			
//			stm2 = conn.getCon().prepareStatement(sql2);
//			stm2.setBoolean(1, true);
//			stm2.setString(2, sql3);
//			
			
			r=stm2.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }

public static void main(String[] args) throws Exception {
	LitDaoImpl dao = new LitDaoImpl();
	dao.setEnService();

}

// ----------------------FIN -------------------------
}


