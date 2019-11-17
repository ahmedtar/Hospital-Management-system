package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Medecin extends Personne {
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int id;
	private Departement dept;
	private String specialisation;
	private Patient[] patients;

	


	public Medecin(String nom, String prenom, String sex, int age, String numTel, String adresse, Departement dept,
			String specialisation, Patient[] patients) {
		super(nom, prenom, sex, age, numTel, adresse);
		this.dept = dept;
		this.specialisation = specialisation;
		this.patients = patients;
		
		id= count.incrementAndGet();
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


	public int getId() {
		return id;
	}


	public Patient[] getPatients() {
		return patients;
	}


	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}
	
	
	
	
	

	
}
