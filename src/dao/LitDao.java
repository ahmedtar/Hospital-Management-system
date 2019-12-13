package dao;

import java.util.List;

import model.Lit;

public interface LitDao {

	List<Lit> searchLit(int id) throws Exception;

	List<Lit> searchLitByChambre(int chambreId) throws Exception;

}
