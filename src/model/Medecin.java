package model;


public class Medecin extends Personne {
	private int id;
	private String specialisation;
	private boolean enservice;
	


	public Medecin(int id,String nom, String prenom,String cne, String sex, int age, String numTel,
			String adresse,String specialisation,boolean enservice) {
		super(nom, prenom,cne, sex, age, numTel, adresse);
		this.id=id;
		this.specialisation = specialisation;
		this.enservice=enservice;
	}
	
	

	//Constructeur ajouté
	public Medecin(String nom, String prenom, String cne, String sexe, int age, String numTel, String adresse, int id) {
		super(nom, prenom, cne, sexe, age, numTel, adresse);
		this.id = id;
	}


<<<<<<< HEAD


=======
>>>>>>> 074fba8e2134e1758a8d72e9f7743346a8c74413
	public int getId() {
		return id;
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
				
				 ", ensevice "+this.isEnservice();
	}
	
	
	
	
	

	
}
