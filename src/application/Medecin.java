package application;

public class Medecin extends Personne {
	
	private String id;
	private Departement dept;
	private String specialisation;
	private Patient[] patients;

	


	public Medecin(String nom, String prenom, String sex, int age, String numTel, String adresse, Departement dept,
			String specialisation, Patient[] patients) {
		super(nom, prenom, sex, age, numTel, adresse);
		this.dept = dept;
		this.specialisation = specialisation;
		this.patients = patients;
	}


	public Departement getDept() {
		return dept;
	}


	public void setDept(Departement dept) {
		this.dept = dept;
	}


	public String getSpecialisation() {
		return specialisation;
	}


	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}


	public String getId() {
		return id;
	}


	public Patient[] getPatients() {
		return patients;
	}


	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}
	
	
	
	
	

	
}
