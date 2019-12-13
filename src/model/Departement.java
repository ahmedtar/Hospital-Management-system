package model;

import java.util.List;

public class Departement {

	private int id;
	private String nom;
	private List<Medecin> medecins;
	public Departement(int id, String nom, List<Medecin> medecins) {
		super();
		this.id = id;
		this.nom = nom;
		this.medecins = medecins;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Medecin> getMedecins() {
		return medecins;
	}
	public void setMedecins(List<Medecin> medecins) {
		this.medecins = medecins;
	}


}
