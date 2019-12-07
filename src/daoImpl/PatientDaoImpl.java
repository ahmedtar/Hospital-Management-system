package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.PatientDao;
import model.Patient;


public class PatientDaoImpl implements PatientDao {


public int addPatient(Patient patient) throws SQLException   {
	ConnectionDB con=new ConnectionDB();
	PreparedStatement myStmt = null;
	int r=0;
	try {
		// prepared statement
		myStmt = con.getCon().prepareStatement("insert into patient"
				+ " (nom, prenom, cne, age,dateEntree,numTel,sexe,adresse,maladie)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		
		myStmt.setString(1, patient.getNom());
		myStmt.setString(2, patient.getPrenom());
		myStmt.setString(3,patient.getCne());
		myStmt.setInt(4, patient.getAge());
		myStmt.setString(5,patient.getDateEntree());
		myStmt.setString(6,patient.getNumTel());
		myStmt.setString(7, patient.getSexe());
		myStmt.setString(8, patient.getAdresse());
		myStmt.setString(9, patient.getMaladie());
		
		
		
		r=myStmt.executeUpdate();			
	} catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		con.getCon().close();
	}
	return r;
}


public int updatePatient(Patient patient) throws SQLException {
	ConnectionDB con=new ConnectionDB();
	PreparedStatement myStmt = null;
	int r=0;

	try {
		// prepared statement
		myStmt = con.getCon().prepareStatement("update patient"
				+ " set nom=?, prenom=?, cne=?, age=?,dateEntree=?,numTel=?,sexe=?,adresse=?,maladie=?"
				+ " where id=?");
		
		
		myStmt.setString(1, patient.getNom());
		myStmt.setString(2, patient.getPrenom());
		myStmt.setString(3,patient.getCne());
		myStmt.setInt(4, patient.getAge());
		myStmt.setString(5,patient.getDateEntree());
		myStmt.setString(6,patient.getNumTel());
		myStmt.setString(7, patient.getSexe());
		myStmt.setString(8, patient.getAdresse());
		myStmt.setString(9, patient.getMaladie());
		myStmt.setInt(10, patient.getId());
		
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







//supprimer patient
public int deletePatient(int patientId) throws SQLException {
	ConnectionDB con=new ConnectionDB();
	PreparedStatement myStmt = null;
	int r=0;
	try {
		// prepare statement
		myStmt = con.getCon().prepareStatement("delete from patient where id=?");
		
		// set param
		myStmt.setInt(1, patientId);
		
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






public List<Patient> getAllPatients() throws Exception {
	ConnectionDB con=new ConnectionDB();
	List<Patient> list = new ArrayList<>();
	
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		myStmt = con.getCon().createStatement();
		myRs = myStmt.executeQuery("select * from patient order by nom");
		
		while (myRs.next()) {
			Patient tempPatient = convertRowToPatient(myRs);
			list.add(tempPatient);
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







public List<Patient> searchPatient(String nom) throws Exception {
	ConnectionDB con=new ConnectionDB();
	List<Patient> list = new ArrayList<>();

	PreparedStatement myStmt = null;
	ResultSet myRs = null;

	try {
		nom += "%";
		myStmt = con.getCon().prepareStatement("select * from patient where nom like ?  order by nom");
		
		myStmt.setString(1, nom);
		
		myRs = myStmt.executeQuery();
		
		while (myRs.next()) {
			Patient tempPatient = convertRowToPatient(myRs);
			list.add(tempPatient);
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


private Patient  convertRowToPatient(ResultSet myRs) throws Exception {
	
	int id = myRs.getInt("id");
	String nom = myRs.getString("nom");
	String prenom = myRs.getString("prenom");
	String cne = myRs.getString("cne");
	int age = myRs.getInt("age");
	String dateEntree = myRs.getString("dateEntree");
	String dateSortie = myRs.getString("dateSortie");
	System.out.println();
	String numTel = myRs.getString("numTel");
	String sexe = myRs.getString("sexe");
	String adresse = myRs.getString("adresse");
	String maladie = myRs.getString("maladie");
	//int litId=myRs.getInt("lit");
	//int medecinId=myRs.getInt("medecin");
	Patient tempPatient = new Patient(nom, prenom, cne, sexe, age, numTel, adresse, maladie, dateEntree);
	tempPatient.setId(id);
	tempPatient.setDateSortie(dateSortie);
	tempPatient.setLit(null);
	tempPatient.setMedecin(null);
	
	return tempPatient;

 }

}

