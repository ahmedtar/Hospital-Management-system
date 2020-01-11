package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LitDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Chambre;
import model.Lit;


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
		boolean enService = myRs.getBoolean("enService");
		int chambreId=myRs.getInt("chambre");
		
		
//		Patient patient=Pdao.searchPatientByLit(id);
		ChambreDaoImpl dao=new ChambreDaoImpl();
	//	Chambre chambre=dao.searchChambre(chambreId);
		
		
		Lit lit=new Lit(id, enService , null);
		return lit;

	 }

public static void main(String[] args) throws Exception {
	LitDaoImpl dao = new LitDaoImpl();
	
	System.out.println(new LitDaoImpl().searchLitById(1).getId());
}

// ----------------------FIN -------------------------
}


