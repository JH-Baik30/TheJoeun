// singleton
package app.bankProject.ver6_DB11;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBAction {
	private static DBAction instance = new DBAction();
	public Connection conn = null;
	public DBAction() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/app";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "java");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DBAction getInstance() {
		if(instance == null) new DBAction();
		return instance;
	}
	public Connection getConnection() {
		return this.conn;
	}
}
