package dao;

import java.util.List;

import javafx.collections.ObservableList;
import model.Lit;

public interface LitDao {

	ObservableList<Lit> searchLit(int id) throws Exception;

	List<Lit> searchLitByChambre(int chambreId) throws Exception;
	
	Lit searchLitById(int id) throws Exception;
	ObservableList<Lit> getLitsEnService() throws Exception;
	public ObservableList<Lit> getLitsHorsService() throws Exception;

}
