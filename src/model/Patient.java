package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Patient extends Personne {
	//private static final AtomicInteger count = new AtomicInteger(0);
	
	private int id;
//	private Chambre chambre;
	private Lit lit;
	private String maladie;
	private Date dateEntree;
	private Date dateSortie;
	private Medecin medecin;
	

	//l'id n'est pas defini dans le constructeur et n'a pas de setters 
	//car la base de données permet de le generer automatiquement 
	//en cochant la case A_I lors du creation du colonne id

	public Patient(String nom, String prenom,String cne, String sex, int age, String numTel, String adresse,
			String maladie, String dateEntree) {
		super(nom, prenom, cne,sex, age, numTel, adresse);
		this.maladie = maladie;
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		this.dateEntree =new Date(sdf.format(dateEntree));
				//id= count.incrementAndGet();
	}

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id=id;
		
	}
	
	public void setMaladie(String maladie) {
		this.maladie = maladie;
	}

	public String getMaladie() {
		return maladie;
	}
	
	public void setDateEntree(String dateEntree) {
		this.dateEntree = new Date(dateEntree);
	}

	public String getDateEntree() {
		SimpleDateFormat sdfDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		return sdfDateFormat.format(dateEntree);
	}
	
	public void setDateSortie(String dateSortie) {
		this.dateSortie = new Date(dateSortie);
	}

	public String getDateSortie() {
		SimpleDateFormat sdfDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		return sdfDateFormat.format(dateSortie);
	}

	/*public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}*/

	

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@Override
	public String toString() {
		return "Patient [ "+super.toString()+ ", maladie=" + maladie + ", dateEntree=" + dateEntree + ", dateSortie="
				+ dateSortie + ", medecin=" + medecin.getNom() +" "+medecin.getPrenom() +"]";
	}

	public Lit getLit() {
		return lit;
	}

	public void setLit(Lit lit) {
		this.lit = lit;
	}

	





	
	
}
