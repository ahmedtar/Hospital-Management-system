package model;

import java.util.List;

public class Chambre {
	private int id;
	private boolean enService;
	private List<Lit> lits;

	public Chambre(int id, boolean enService, List<Lit> lits) {
		super();
		this.id = id;
		this.enService = enService;
		this.lits = lits;
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
	public List<Lit> getLits() {
		return lits;
	}
	public void setLits(List<Lit> lits) {
		this.lits = lits;
	}
	
	
	
	
}
