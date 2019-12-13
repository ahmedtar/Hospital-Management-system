package dao;


import model.Chambre;
import model.Lit;

public interface ChambreDao {

	Chambre searchChambre(int id) throws Exception;

	Chambre searchChambreByLit(Lit lit) throws Exception;

}
