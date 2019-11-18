package model;

import java.sql.SQLException;
import java.util.Date;
public class Main {
		
		
	public static void main(String[] args) throws SQLException {
		Departement departement=new Departement("radiologie");
		Medecin m=new Medecin("ahmed2", "elatrouz", "homme", 20, "0632302864","N 33 bis marrakech",departement,"radiologist" );
		
		ConnectionDB con=new ConnectionDB();
		Patient p=new Patient("ahmed2", "elatrouz", "homme", 20, "0632302864","N 33 bis marrakech", "cancer","10/10/1999" );
		
		p.setMedecin(m);
		System.out.println(p.toString());
		System.out.println(m.toString());
	}

}
