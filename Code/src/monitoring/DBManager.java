package monitoring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Gestion de la BDD
 * @author 
 *
 */
public class DBManager {

	private static DBManager instance;

	private ResourceBundle properties;

	private static String resourceBundle="config";
	
	private DBManager() {
		properties = ResourceBundle.getBundle(resourceBundle);
		
		try {
			Class.forName(properties.getString("DB_DRIVER"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static DBManager getInstance() {
		if (instance == null) {
			synchronized (DBManager.class) {
				instance = new DBManager();
			}
		}
		return instance;
	}

	public Connection getConnection() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getString("JDBC_URL"), properties.getString("DB_LOGIN"), properties.getString("DB_PASSWORD"));
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return connection;

	}
	
	public void cleanup(Connection connection, Statement stat, ResultSet rs) {
		if (rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stat!=null) {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Connection c = DBManager.getInstance().getConnection();
		if (c!=null) {
			System.out.println(c.toString());
		}
	}
}
