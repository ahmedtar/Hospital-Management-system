package application;

public class Lit {
	private String id;
	private boolean enService;
	private Patient patient;
	private Chambre chambre;
	
	
	


	public Lit(boolean enService, Patient patient, Chambre chambre) {
		super();
		this.enService = enService;
		this.patient = patient;
		this.chambre = chambre;
	}

	public String getId() {
		return id;
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
