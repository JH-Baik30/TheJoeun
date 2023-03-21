package app.bankProject.ver6_DB5;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class BankDbCreate {
	public static void main(String[] args) throws IOException {
		Connection conn = DBAction.getInstance().getConnection();
		Statement stmt = null;
	
		try {
			stmt = conn.createStatement();
			String sql = createTable();
			System.out.println(1);
			int result = stmt.executeUpdate(sql);
			String msg = result > -1 ? "성공" : "실패";
			System.out.println("DB Create Success");
		} catch (Exception e) {
			System.out.println("Connect Fail!!!!!!!");
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e2) {	}
		}
	}
	
	public static String createTable() {
		String sql = "CREATE TABLE BANKDB("
					+ "ACCOUNT INTEGER, "
					+ "ID VARCHAR(10) UNIQUE, "
					+ "PW VARCHAR(10) NOT NULL, "
					+ "NAME VARCHAR(5) NOT NULL, "
					+ "BALANCE INTEGER DEFAULT 0, "
					+ "CREATE_DATE DATETIME)"; 
		return sql;
	}
}
