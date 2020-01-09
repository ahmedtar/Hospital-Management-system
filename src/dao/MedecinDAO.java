package dao;

import java.util.List;

import model.Medecin;

public interface MedecinDAO {
	
	public int addMedecin(Medecin mdc);
	public int updateMedecin(Medecin mdc);
	public int deleteMedecin(Medecin mdc);
	
	public List<Medecin> getAllMdcs();
	public List<Medecin> searhMdc(String colomn,String str);
	public List<Medecin> searhDepartementDeMdc(int d);
	public List<Medecin> searhInServiceMdcs(boolean m);
	
	
}
