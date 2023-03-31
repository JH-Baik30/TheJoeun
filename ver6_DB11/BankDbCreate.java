package app.bankProject.ver6_DB11;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class BankDbCreate {
	static Connection conn = DBAction.getInstance().getConnection();
	static Statement stmt = null;
	public static void main(String[] args) throws IOException {
		BankDbCreate create = new BankDbCreate();

//		create.createPkTable();
//		create.createfkTable();
		create.alter();
	}
	
	public void DBcreater(String sql) {
		
		try {
			stmt = conn.createStatement();
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
	
	public String createPkTable() {
		String sql = "CREATE TABLE ynbank0331("
					+ "ACCOUNT INTEGER, "
					+ "ID VARCHAR(20) PRIMARY KEY, "
					+ "PW VARCHAR(20) NOT NULL, "
					+ "NAME VARCHAR(20) NOT NULL, "
					+ "MOBILE VARCHAR(15) NOT NULL, "
					+ "email VARCHAR(30), "
					+ "BALANCE INTEGER DEFAULT 0, "
					+ "CREATE_DATE DATETIME, "
					+ "BIRTH_DAY DATE)"; 
		DBcreater(sql);
		return "pktable 생성";
	}
	
	public String createfkTable() {
		String sql = "CREATE TABLE ynbank0331transactiondb ("
					+ "ACCOUNT INT, "
					+ "ID VARCHAR(20), "
					+ "WITH_DRAW INT NULL DEFAULT 0, "
					+ "DEPOSIT INT NULL DEFAULT 0, "
					+ "TRANSFER INT NULL DEFAULT 0, "
					+ "SUBJECT VARCHAR(20), "
					+ "TRANSACTION_DATE DATETIME)";
		DBcreater(sql);
		return "fktable 생성";
	}
	
	// 워크벤치에서 입력시만 적용이 됨.
	public String alter() {
		String sql = "ALTER TABLE ynbank0331transactiondb "
					+ "ADD FOREIGN KEY(ID) "
					+ "REFERENCES BANKDB(ID) ON DELETE CASCADE";
		return sql;
	}
}
