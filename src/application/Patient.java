package application;

import java.util.Date;

public class Patient extends Personne {
	
	private String id;
	private Chambre chambre;
	private String maladie;
	private Date dateEntree;
	private Date dateSortie;
	

	//l'id n'est pas defini dans le constructeur et n'a pas de setters 
	//car la base de données permet de le generer automatiquement 
	//en cochant la case A_I lors du creation du colonne id

	public Patient(String nom, String prenom, String sex, int age, String numTel, String adresse, Chambre chambre,
			String maladie, Date dateEntree, Date dateSortie) {
		super(nom, prenom, sex, age, numTel, adresse);
		this.chambre = chambre;
		this.maladie = maladie;
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
	}

	public String getId() {
		return id;
	}

	
	public void setMaladie(String maladie) {
		this.maladie = maladie;
	}

	public String getMaladie() {
		return maladie;
	}
	
	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Date getDateEntree() {
		return dateEntree;
	}
	
	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}





	
	
}
