package dao;

import java.sql.SQLException;
import java.util.List;

import model.Patient;

public interface PatientDao {
	
	 int addPatient(Patient patient) throws SQLException;
	 int updatePatient(Patient patient) throws SQLException ;
	 int deletePatient(int patientId) throws SQLException;
	 List<Patient> getAllPatients() throws Exception;
	 List<Patient> searchPatient(String nom) throws Exception;
	 List<Patient> searchPatientByMaladie(String maladie) throws Exception;
	List<Patient> searchPatientByDateEntree(String dateEntree) throws Exception;
	List<Patient> searchPatientByCne(String cne) throws Exception;
}
