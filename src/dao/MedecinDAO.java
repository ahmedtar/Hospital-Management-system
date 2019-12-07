package dao;

import java.util.List;

import model.Medecin;

public interface MedecinDAO {
	
	public void addMedecin(Medecin mdc);
	public void updateMedecin(Medecin mdc,Medecin mdcmodifier);
	public List<Medecin> getAllMdcs();
	public List<Medecin> searhMdc(String colomn,String str);
	public List<Medecin> searhDepartementDeMdc(int d);
	public List<Medecin> searhInServiceMdcs(boolean m);
	public void deleteMedecin(int id);
	
}
