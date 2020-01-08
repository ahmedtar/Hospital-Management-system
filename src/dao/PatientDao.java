package dao;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.ObservableList;
import model.Patient;

public interface PatientDao {
	
	 int addPatient(Patient patient) throws SQLException;
	 int updatePatient(Patient patient) throws SQLException ;
	 int deletePatient(int patientId) throws SQLException;
	 List<Patient> getAllPatients() throws Exception;
	 public ObservableList<Patient> searchPatient(String n) throws Exception;
	 List<Patient> searchPatientByMaladie(String maladie) throws Exception;
	List<Patient> searchPatientByDateEntree(String dateEntree) throws Exception;
	List<Patient> searchPatientByCne(String cne) throws Exception;
	Patient searchPatientByLit(int litId) throws Exception;
}
