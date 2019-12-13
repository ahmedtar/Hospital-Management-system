package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LitDao;
import model.Chambre;
import model.Lit;
import model.Patient;


public class LitDaoImpl implements LitDao {

	
	
	@Override
	public List<Lit> searchLit(int id) throws Exception {
		
		ConnectionDB con=new ConnectionDB();
		List<Lit> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = con.getCon().prepareStatement("select * from lit where id=?  order by id");
			
			myStmt.setInt(1, id);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
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
	
	ChambreDaoImpl dao=new ChambreDaoImpl();
		PatientDaoImpl Pdao=new PatientDaoImpl();
		int id = myRs.getInt("id");
		boolean enService = myRs.getBoolean("enService");
		int chambreId=myRs.getInt("chambre");
		
		
		Patient patient=Pdao.searchPatientByLit(id);
		
		Chambre chambre=dao.searchChambre(chambreId);
		
		
		Lit lit=new Lit(id, enService, patient, chambre);
		return lit;

	 }
	
}
