package model;

public abstract class Personne {
	private String nom;
	private String cne;
	private String prenom;
	private String sexe;
	private int age;
	private String numTel;
	private String adresse;
	
	

	public Personne(String nom, String prenom, String cne, String sexe, int age, String numTel, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cne=cne;
		this.sexe = sexe;
		this.age = age;
		this.numTel = numTel;
		this.adresse=adresse;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

/*	@Override
	public String toString() {
		return " nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", age=" + age + ", numTel=" + numTel
				+ ", adresse=" + adresse ;
	}*/

	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}

	@Override
	public String toString() {
		return " cne=" + cne + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", age=" + age
				+ ", numTel=" + numTel + ", adresse=" + adresse ;
	}

	
	
	
	
}
