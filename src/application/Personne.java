package application;

public class Personne {
	private String nom;
	private String prenom;
	private String sex;
	private int age;
	private String numTel;
	private String adresse;
	
	

	public Personne(String nom, String prenom, String sex, int age, String numTel, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sex = sex;
		this.age = age;
		this.numTel = numTel;
		this.setAdresse(adresse);
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	
	
	
}
