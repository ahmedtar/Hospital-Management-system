package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Departement {

	//private static final AtomicInteger count = new AtomicInteger(0);
	private int id;
	private String nom;
	private Medecin[] medecins;
	//private Chambre[] chambres;

	//l'id n'est pas defini dans le constructeur et n'a pas de setters 
	//car la base de données permet de le generer automatiquement 
	//en cochant la case A_I lors du creation du colonne id
	

	public Departement(String nom) {
		super();
		this.nom = nom;
		//id= count.incrementAndGet();
	}
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Medecin[] getMedecins() {
		return medecins;
	}
	public void setMedecins(Medecin[] medecins) {
		this.medecins = medecins;
	}
/*	public Chambre[] getChambres() {
		return chambres;
	}
	public void setChambres(Chambre[] chambres) {
		this.chambres = chambres;
	}
	*/
	
}
