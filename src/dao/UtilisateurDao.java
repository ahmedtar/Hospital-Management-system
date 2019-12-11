package dao;



import model.Utilisateur;

public interface UtilisateurDao {

	int login(String login,String password);
	int AddUtilisateur(Utilisateur u);
	int deleteUtilisateur(int id);
}
