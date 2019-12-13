package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.ChambreDao;
import model.Chambre;
import model.Lit;


public class ChambreDaoImpl implements ChambreDao {

	
	@Override
	public Chambre searchChambre(int id) throws Exception {
		
		ConnectionDB con=new ConnectionDB();
		Chambre chambre=null;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = con.getCon().prepareStatement("select * from chambre where id=?");
			
			myStmt.setInt(1, id);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				chambre= convertRowToChambre(myRs);
			}
			
			
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		finally {
			con.close();
		}
		return chambre;

	}
	
	
	@Override
	public Chambre searchChambreByLit(Lit lit) throws Exception {
		
		ConnectionDB con=new ConnectionDB();
		Chambre chambre=null;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = con.getCon().prepareStatement("select * from chambre where id=?");
			
			myStmt.setInt(1, lit.getChambre().getId());
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				chambre= convertRowToChambre(myRs);
			}
			
			
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		finally {
			con.close();
		}
		return chambre;

	}
	
	
	
	
	
	
	
	
	private Chambre convertRowToChambre(ResultSet myRs) throws Exception {
		
		
			LitDaoImpl Ldao=new LitDaoImpl();
			
			int id = myRs.getInt("id");
			boolean enService = myRs.getBoolean("enService");
			List <Lit> lits=Ldao.searchLitByChambre(id);
			
			Chambre ch=new Chambre(id, enService,lits);
			return ch;
			
		 }
		
	}

