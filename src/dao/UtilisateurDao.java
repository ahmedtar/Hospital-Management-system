package dao;



import java.sql.SQLException;
import java.util.List;

import model.Utilisateur;

public interface UtilisateurDao {

	
	int AddUtilisateur(Utilisateur u);
	int deleteUtilisateur(int id);
	int login(String login,String password)throws SQLException;
	int updateUtilisateur(Utilisateur utilisateur) throws SQLException;
	List<Utilisateur> getAllUtilisateurs() throws Exception;
}
