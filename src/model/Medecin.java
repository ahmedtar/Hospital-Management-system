package model;


public class Medecin extends Personne {
	private int id;
	private String specialisation;
	private int departement;
	private boolean enservice;
	


	public Medecin(int id,String nom, String prenom,String cne, String sex, int age, String numTel,
			String adresse,String specialisation, int departement,boolean enservice) {
		super(nom, prenom,cne, sex, age, numTel, adresse);
		this.id=id;
		this.specialisation = specialisation;
		this.departement = departement;
		this.enservice=enservice;
	}
	
	
<<<<<<< HEAD
	
=======
	//Constructeur ajout�
	public Medecin(String nom, String prenom, String cne, String sexe, int age, String numTel, String adresse, int id) {
		super(nom, prenom, cne, sexe, age, numTel, adresse);
		this.id = id;
	}



>>>>>>> 2419ded503aa47fdafa75bc76235f2c8559325be
	public int getId() {
		return id;
	}

	public int getDepartement() {
		return departement;
	}


	public void setdepartement(int dept) {
		this.departement = dept;
	}


	public String getSpecialisation() {
		return specialisation;
	}


	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public boolean isEnservice() {
		return enservice;
	}


	public void setEnservice(boolean enservice) {
		this.enservice = enservice;
	}
	
	
	@Override
	public String toString() {
		return "Medecin : "+super.toString()+ ", specialisation=" +this.specialisation+ 
				", departement=" + this.getDepartement() +", ensevice "+this.isEnservice();
	}
	
	
	
	
	

	
}
