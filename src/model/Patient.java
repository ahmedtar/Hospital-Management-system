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
	public void setDateSortie(String date) {

		if(!(date==null)) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String string=sdf.format(new Date(date));
		this.dateSortie =new Date(string);
		}
		else this.dateSortie =null;
		
	}

	public String getDateSortie() {

		 SimpleDateFormat sdfDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		 if(this.dateSortie==null) return null;
		return sdfDateFormat.format(dateSortie);
	}
	

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}


	public Lit getLit() {
		return lit;
	}

	public void setLit(Lit lit) {
		this.lit = lit;
	}

	@Override
	public String toString() {
		return "Patient [Id=" + getId() + ","+super.toString()+ ", Maladie=" + getMaladie() + ", DateEntree=" + getDateEntree()
				+ ", DateSortie=" + getDateSortie() + ", Medecin=" + getMedecin() + ", Lit=" + getLit()
				+ "]";
	}

	





	
	
}
