package model;

import java.sql.SQLException;

public class Main {
		
		
	public static void main(String[] args) throws SQLException {
		
		ConnectionDB con=new ConnectionDB();
		Personne p=new Personne("ahmed2", "elatrouz", "homme", 20, "0632302864","N 33 bis marrakech");
		
		//con.inserer(p);  should make inserer(Personne)  public first!!
	}

}
