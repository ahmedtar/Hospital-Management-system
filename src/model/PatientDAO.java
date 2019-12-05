package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PatientDAO {
	

	private Connection con;
	private String url="jdbc:mysql://localhost:3306/hopital";
	private String user="root";
	private String pass="";
	
public PatientDAO() throws Exception {
	
		con = DriverManager.getConnection(url, user, pass);
		System.out.println(" connection etablie a: " + url);
	}


public void addPatient(Patient patient) throws Exception {
	PreparedStatement myStmt = null;

	try {
		// prepared statement
		myStmt = con.prepareStatement("insert into patient"
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
		
		
		
		myStmt.executeUpdate();			
	}
	
	
	finally {
		close(myStmt);
	}
	System.out.println("Patient bien ajoutée!!");
	
}


public void updatePatient(Patient patient) throws SQLException {
	PreparedStatement myStmt = null;

	try {
		// prepared statement
		myStmt = con.prepareStatement("update patient"
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
		
		myStmt.executeUpdate();			
	}
	
	
	finally {
		close(myStmt);
	}
	System.out.println("Le patient est a jour !!");
	
}

//supprimer patient
public void deletePatient(int patientId) throws SQLException {
	PreparedStatement myStmt = null;

	try {
		// prepare statement
		myStmt = con.prepareStatement("delete from patient where id=?");
		
		// set param
		myStmt.setInt(1, patientId);
		
		// execute SQL
		myStmt.executeUpdate();			
	}
	finally {
		close(myStmt);
	}
	System.out.println("patient bien supprimé!");
}


public List<Patient> getAllPatients() throws Exception {
	List<Patient> list = new ArrayList<>();
	
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		myStmt = con.createStatement();
		myRs = myStmt.executeQuery("select * from patient order by nom");
		
		while (myRs.next()) {
			Patient tempPatient = convertRowToPatient(myRs);
			list.add(tempPatient);
		}

		return list;		
	}
	finally {
		close(myStmt, myRs);
	}
}




public List<Patient> searchPatient(String nom) throws Exception {
	List<Patient> list = new ArrayList<>();

	PreparedStatement myStmt = null;
	ResultSet myRs = null;

	try {
		nom += "%";
		myStmt = con.prepareStatement("select * from patient where nom like ?  order by nom");
		
		myStmt.setString(1, nom);
		
		myRs = myStmt.executeQuery();
		
		while (myRs.next()) {
			Patient tempPatient = convertRowToPatient(myRs);
			list.add(tempPatient);
		}
		
		return list;
	}
	finally {
		close(myStmt, myRs);
	}
}


private Patient  convertRowToPatient(ResultSet myRs) throws SQLException {
	
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

private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
		throws SQLException {

	if (myRs != null) {
		myRs.close();
	}

	if (myStmt != null) {
		
	}
	
	if (myConn != null) {
		myConn.close();
	}
}

private void close(Statement myStmt, ResultSet myRs) throws SQLException {
	close(null, myStmt, myRs);		
}

private void close(Statement myStmt) throws SQLException {
	close(null, myStmt, null);		
}

}
