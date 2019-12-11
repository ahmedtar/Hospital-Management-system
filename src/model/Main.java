package model;

import java.sql.SQLException;
import java.util.List;

import dao.UtilisateurDao;
import daoImpl.PatientDaoImpl;
import daoImpl.UtilisateurDaoImpl;

public class Main {
		
	public static void main(String[] args) throws Exception {
		//Departement departement=new Departement("departement1");
		//Medecin m=new Medecin("smiytTbiba", "kniytha", "EE564789", "femme", 20, "0630052847","N 33 bis marrakech","Geriatrie Autonomie","radio" );
		
		//ConnectionDB con=new ConnectionDB();
		//con.insere(departement);
	Patient p=new Patient("ahmed", "elatrouz","EE123456", "homme", 20, "0635555864","N 33 bis marrakech", "cancer","09/10/2013" );
		//System.out.println(p.getDateEntree());
		//p.setDateSortie("10/10/1999");
		//p.setMedecin(m);
		//System.out.println(con.insere(departement));
		//System.out.println(con.inserer(m));
	//	System.out.println(con.inserer(p));
		//System.out.println(p.toString());
		//System.out.println(m.toString());
		
		//
		//System.out.println(p.getDateSortie());
	//p.setDateSortie("05/11/2016");
	PatientDaoImpl dao=new PatientDaoImpl();
	//System.out.println(dao.addPatient(p));
	Utilisateur u1=new Utilisateur(1, "hooola", "ahmed", "kniyti", "password", true, false);
	UtilisateurDao dao1=new UtilisateurDaoImpl();
	System.out.println(dao1.updateUtilisateur(u1));
	//System.out.println(dao1.AddUtilisateur(u1));
	//System.out.println(dao.searchPatientByCne("EE123456"));
	}

}
