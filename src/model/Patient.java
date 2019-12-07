package model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Patient extends Personne {
	
	private int id;
	private Lit lit;
	private String maladie;
	private Date dateEntree;
	private Date dateSortie;
	private Medecin medecin;
	

	@SuppressWarnings("deprecation")
	public Patient(String nom, String prenom,String cne, String sex, int age, String numTel, String adresse,
			String maladie, String dateEntree) {
		super(nom, prenom, cne,sex, age, numTel, adresse);
		this.maladie = maladie;
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		this.dateEntree =new Date(sdf.format(new Date(dateEntree)));
		
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
	
	@SuppressWarnings("deprecation")
	public void setDateEntree(String dateEntree) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		this.dateEntree =new Date(sdf.format(new Date(dateEntree)));
	}

	public String getDateEntree() {
		SimpleDateFormat sdfDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		return sdfDateFormat.format(dateEntree);
	}
	
	@SuppressWarnings("deprecation")
	public void setDateSortie(String dateSortie) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		this.dateSortie =new Date(sdf.format(new Date(dateSortie)));
	}

	public String getDateSortie() {
		SimpleDateFormat sdfDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		return sdfDateFormat.format(dateSortie);
	}
	

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
