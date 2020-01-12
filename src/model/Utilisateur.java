package model;

public class Utilisateur {
	
	private int id;
	private String login;
	private String nom;
	private String prenom;
	private String password;
	private boolean estActif; 
	private boolean Admin; 
	public Utilisateur(int id, String login, String nom, String prenom, String password, boolean estActif, Boolean Admin) {
		super();
		this.id = id;
		this.login = login;
		this.nom = nom;
		this.prenom = prenom; 
		this.password = password;
		this.estActif = estActif;
		this.Admin = Admin;
	}
	
	public Utilisateur() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean EstActif() {
		return estActif;
	} 
	public void setEstActif(boolean estActif) {
		this.estActif = estActif;
	}
	
	public boolean isAdmin() {
		return Admin;
	}

	public void setAdmin(boolean Admin) {
		this.Admin = Admin;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", nom=" + nom + ", prenom=" + prenom + ", password="
				+ password + ", estActif=" + estActif + ", Admin=" + Admin + "]";
	}

	
	
	
	
	
}
