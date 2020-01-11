package model;

public class Lit {

	
	private int id;
	private boolean enService;
	private Patient patient;
	private Chambre chambre;
	
	
	public Lit(int id, boolean enService, Patient patient, Chambre chambre) {
		super();
		this.id = id;
		this.enService = enService;
		this.patient = patient;
		this.chambre = chambre;
	}
	
	
	
	@Override
	public String toString() {
		return "Lit [id=" + id + ", enService=" + enService + ", ]";
	}



	public Lit(int id, boolean enService, Chambre chambre) {
		super();
		this.id = id;
		this.enService = enService;
		this.chambre = chambre;
	}



	//Constructeurs ajoutés
	public Lit() {
		super();
	}
	
	public Lit(int id) {
	super();
	this.id = id;
}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public boolean isEnService() {
		return enService;
	}
	public void setEnService(boolean enService) {
		this.enService = enService;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Chambre getChambre() {
		return chambre;
	}
	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}
	
	
	

}
