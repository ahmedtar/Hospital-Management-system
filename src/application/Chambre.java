package application;

public class Chambre {
	
	private String id;
	private Departement dept;
	private Patient[] patients;
	private boolean enService;
	private Lit[] lits;
	
	//l'id n'est pas defini dans le constructeur et n'a pas de setters 
	//car la base de données permet de le generer automatiquement 
	//en cochant la case A_I lors du creation du colonne id
	public Chambre(Departement dept, Patient[] patients, boolean enService, Lit[] lits) {
		super();
		this.dept = dept;
		this.patients = patients;
		this.enService = enService;
		this.lits = lits;
	}


	public String getId() {
		return id;
	}


	public Departement getDept() {
		return dept;
	}


	public void setDept(Departement dept) {
		this.dept = dept;
	}


	public Patient[] getPatients() {
		return patients;
	}


	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}


	public boolean isEnService() {
		return enService;
	}


	public void setEnService(boolean enService) {
		this.enService = enService;
	}


	public Lit[] getLits() {
		return lits;
	}


	public void setLits(Lit[] lits) {
		this.lits = lits;
	}
	
	
	
}
