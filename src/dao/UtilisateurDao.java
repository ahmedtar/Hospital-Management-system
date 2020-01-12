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
	Utilisateur getUserByPassword(String pass) throws Exception;
	Utilisateur getUserByLoginAndPassword(String login , String pass) throws Exception;
	
}
