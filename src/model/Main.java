package model;

import java.sql.SQLException;

public class Main {
		//add comment
		
	public static void main(String[] args) throws SQLException {
		//Departement departement=new Departement("departement1");
		//Medecin m=new Medecin("smiytTbiba", "kniytha", "EE564789", "femme", 20, "0630052847","N 33 bis marrakech","Geriatrie Autonomie","radio" );
		
		//ConnectionDB con=new ConnectionDB();
		//con.insere(departement);
		Patient p=new Patient("ahmed", "elatrouz","EE123456", "homme", 20, "0631155864","N 33 bis marrakech", "cancer","09/10/2013" );
		System.out.println(p.getDateEntree());
		//p.setDateSortie("10/10/1999");
		//p.setMedecin(m);
		//System.out.println(con.insere(departement));
		//System.out.println(con.inserer(m));
	//	System.out.println(con.inserer(p));
		//System.out.println(p.toString());
		//System.out.println(m.toString());
		
		p.setDateSortie("05/11/2016");
		System.out.println(p.getDateSortie());
	}

}
