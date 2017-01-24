package monitoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe permettant la connexion à une BDD pour gérer le modèle Panne
 * @author Christophe Thao Ky & Adam Ouachani
 *
 */
public class PanneDAOImpl implements  PanneDAO{

	public List<Panne> getAll() throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes");

		int idPanne;
		String name;
		String idMachine;
		String typeMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();
		return pannes;

	}
	
	
	public List<Panne> getPanneByTypeMachine(String typeMachine) throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE typeMachine='"+typeMachine+"'");

		int idPanne;
		String name;
		String idMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();
		return pannes;

	}

	public int getNbPannesMin() throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();
		int nbPannes;

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 60");
		if (rs.last()) {
			nbPannes = rs.getRow();
		}
		else {
			nbPannes = 0;
		}
		connexion.close();
		return nbPannes;
	}

	public int getNbPannesHeure() throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();
		int nbPannes;
		
		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 3600");

		if (rs.last()) {
			nbPannes = rs.getRow();
		}
		else {
			nbPannes = 0;
		}
		connexion.close();
		return nbPannes;
	}

	public int getNbPannesJour() throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();
		int nbPannes;
		
		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 86400");
		if (rs.last()) {
			nbPannes = rs.getRow();
		}
		else {
			nbPannes = 0;
		}
		connexion.close();
		return nbPannes;
	}

	public int getNbPannesMois() throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();
		int nbPannes;
		
		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP)  < 2592000");

		if (rs.last()) {
			nbPannes = rs.getRow();
		}
		else {
			nbPannes = 0;
		}
		connexion.close();
		return nbPannes;
	}

	public List<Panne> getPannesByMinute() throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 60");

		int idPanne;
		String name;
		String idMachine;
		String typeMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();

		return pannes;

	}

	public List<Panne> getPannesByHeure() throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 3600");

		int idPanne;
		String name;
		String idMachine;
		String typeMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();

		return pannes;

	}

	public List<Panne> getPannesByJour() throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 86400");

		int idPanne;
		String name;
		String idMachine;
		String typeMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();

		return pannes;

	}

	public List<Panne> getPannesByMois() throws SQLException{
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 2592000");

		int idPanne;
		String name;
		String idMachine;
		String typeMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();

		return pannes;

	}

	public String getDate() throws SQLException {
		String date = new String();
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT NOW()");

		rs.last();
		date = rs.getString("now()");
		connexion.close();

		return date;
	}

	public void insertNewPanne(String idMachine, String name, String typeMachine) throws SQLException {
		//Connexion a la base de donnees et traitement de la requete SQL.
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		statement = connexion.createStatement();
		statement.executeUpdate("INSERT INTO Pannes (idMachine, typeMachine, name, datePanne) VALUES ('" + idMachine + "','" + typeMachine + "','" + name +"', NOW());");
		WebSocket.maj("new");
		connexion.close();

	}


	public void updatePanne(int idPanne, boolean resolue) throws SQLException {
		//Connexion a la base de donnees et traitement de la requete SQL.
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement;
		statement = connexion.createStatement();
		statement.executeUpdate("UPDATE Pannes set etat="+resolue+" WHERE id="+idPanne);
		WebSocket.maj("update");
		connexion.close();

	}


	@Override
	public List<Panne> getPannesByMinute(String typeMachine) throws SQLException {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 60 AND typeMachine='"+typeMachine+"'");

		int idPanne;
		String name;
		String idMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();
		return pannes;
	}


	@Override
	public List<Panne> getPannesByHeure(String typeMachine) throws SQLException {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 3600 AND typeMachine='"+typeMachine+"'");

		int idPanne;
		String name;
		String idMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();

		return pannes;
	}


	@Override
	public List<Panne> getPannesByJour(String typeMachine) throws SQLException {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 86400 AND typeMachine='"+typeMachine+"'");

		int idPanne;
		String name;
		String idMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();

		return pannes;
	}


	@Override
	public List<Panne> getPannesByMois(String typeMachine) throws SQLException {
		Connection connexion = DBManager.getInstance().getConnection();
		Statement statement = connexion.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM Pannes WHERE TIMESTAMPDIFF(SECOND, datePanne, CURRENT_TIMESTAMP) < 2592000 AND typeMachine='"+typeMachine+"'");

		int idPanne;
		String name;
		String idMachine;
		String date;
		boolean resolue;

		List<Panne> pannes = new LinkedList<Panne>();

		while (rs.next()){
			idPanne = rs.getInt("id");
			name = rs.getString("name");
			idMachine = rs.getString("idMachine");
			typeMachine = rs.getString("typeMachine");
			date = rs.getString("datePanne");
			resolue = rs.getBoolean("etat");

			pannes.add(new  Panne(idPanne,name, idMachine, typeMachine, date,resolue));
		}
		connexion.close();

		return pannes;
	}
}
