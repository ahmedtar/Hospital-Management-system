package model;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Medecin extends Personne {
	//private static final AtomicInteger count = new AtomicInteger(0); 
	private int id;
	private String dept;
	private String specialisation;
	private Patient[] patients;

	


	public Medecin(String nom, String prenom,String cne, String sex, int age, String numTel, String adresse, String dept,
			String specialisation) {
		super(nom, prenom,cne, sex, age, numTel, adresse);
		this.dept = dept;
		this.specialisation = specialisation;
		
	//	id= count.incrementAndGet();
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
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


	@Override
	public String toString() {
		return "Medecin [ "+super.toString()+ ", dept=" + this.getDept() + ", specialisation=" + specialisation + ", patients="
				+ Arrays.toString(patients) + "]";
	}
	
	
	
	
	

	
}
