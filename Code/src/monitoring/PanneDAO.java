package monitoring;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface pour le modèle Panne
 * @author Christophe Thao Ky & Adam Ouechani
 *
 */
public interface PanneDAO {

	public List<Panne> getAll() throws SQLException;
	public List<Panne> getPanneByTypeMachine(String typeMachine) throws SQLException;
	public int getNbPannesMin() throws SQLException;
	public int getNbPannesHeure() throws SQLException;
	public int getNbPannesJour() throws SQLException;
	public int getNbPannesMois() throws SQLException;
	public List<Panne> getPannesByMinute() throws SQLException;
	public List<Panne> getPannesByHeure() throws SQLException;
	public List<Panne> getPannesByJour() throws SQLException;
	public List<Panne> getPannesByMois() throws SQLException;
	public List<Panne> getPannesByMinute(String typeMachine) throws SQLException;
	public List<Panne> getPannesByHeure(String typeMachine) throws SQLException;
	public List<Panne> getPannesByJour(String typeMachine) throws SQLException;
	public List<Panne> getPannesByMois(String typeMachine) throws SQLException;
	public String getDate() throws SQLException;
	public void updatePanne(int idPanne,boolean resolue) throws SQLException;
	public void insertNewPanne(String idMachine,String name, String typeMachine) throws SQLException;
}
