//	Create Read Update Delete
package app.bankProject.ver6_DB7;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBmanagement {
	Connection conn = DBAction.getInstance().getConnection();	
	Statement stmt = null;
	ResultSet rs = null;
	private String msg;
	ArrayList<Member> mem = new ArrayList<>();
	
	public String controlDB(String sql) {
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			msg = result > -1 ? "완료" : "실패";
		} catch (Exception e) {
			System.out.println("데이터베이스 드라이버 로딩및 연결실패!");
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();		// 상태를 닫고
			} catch (Exception e2) {	}			// 연결 끊는 메소드는 아래에.
		}
		return msg;
	}
	
	public ArrayList<Member> readfromDB() {
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM BANKDB";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				mem.add(new Member().setAccount(rs.getInt("account"))
									.setId(rs.getString("id"))
									.setPw(rs.getString("pw"))
									.setName(rs.getString("name"))
									.setBalance(rs.getInt("balance")));
//									.setBirth(rs.getDate("birth_day"));
			}
			
			return mem;
		} catch (Exception e) {
			System.out.println("데이터베이스 드라이버 로딩및 연결실패!");
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();		// 상태를 닫고
			} catch (Exception e2) {	}			// 연결 끊는 메소드는 아래에.
		}
		return null;
	}
	
//	ACCOUNT, ID, PW, NAME, Balance
//	👍 test pass 👍
	public void insertToDB(int account, String id, String pw, String name, String birth) {
		String sql = "INSERT INTO BANKDB VALUES('"
					+ account + "', '" + id + "', '"
					+ pw + "', '" + name + "', ";
//???				+ 0 + ", NOW()) " + Date.valueOf(birth);
		msg = controlDB(sql);
		System.out.println(name + "님의 계좌 개설에 " + msg + " 했습니다.");
	}
	
//	👍 test pass 👍	
	public void updateToDB(int account, int balance) {
		String sql = "UPDATE BANKDB SET BALANCE=" + balance
					+ " WHERE ACCOUNT="+ account;
		msg = controlDB(sql);
	}

//	입금
	public void depositLog(int account, String id, int deposit) {
		String sql = "insert into transactiondb(ACCOUNT, ID, DEPOSIT, TRANSACTION_DATE)"
			+ " values (" + account +", '" + id + "', " + deposit + ", NOW())";
		controlDB(sql);
	}
	
// 이체용 입금
	public void depositLog(int account, String id, int deposit, String name) {
		String sql = "insert into transactiondb(ACCOUNT, ID, DEPOSIT, SUBJECT, TRANSACTION_DATE)"
				+ " values (" + account +", '" + id + "', " + deposit + ", '" + name + "', NOW())";
		controlDB(sql);
	}
	
//	출금
	public void withDrawLog(int account, String id, int WITH_DRAW) {
		String sql = "insert into transactiondb(ACCOUNT, ID, WITH_DRAW, TRANSACTION_DATE)"
				+ " values (" + account +", '" + id + "', " + WITH_DRAW + ", NOW())";
		controlDB(sql);
	}
	
//	이체용 출금
	public void withDrawLog(int account, String id, int TRANSFER, String name) {
		String sql = "insert into transactiondb(ACCOUNT, ID, TRANSFER, SUBJECT, TRANSACTION_DATE)"
				+ " values (" + account +", '" + id + "', " + TRANSFER + ", '" + name +  "', NOW())";
		controlDB(sql);
	}
	
//	👍 test pass 👍
//	public void deleteToDB(String id) {
//		String sql = "DELETE FROM BANKDB WHERE id='"+ id + "'";
//		msg = controlDB(sql);
//		System.out.println("DB 삭제 " + msg + " 했습니다.");
//	}
	
	public void closeDB() throws SQLException {
		if(conn != null) conn.close();
	}
	
// DBadmin 에서 test 용으로 사용하는 메소드
//	public void sqlTestMethod(String sql) {
//		String sql = "INSERT INTO BANKDB VALUES(aaa, '123', 10, NOW())";
//		msg = controlDB(sql);
//		System.out.println("작업 결과 : " + msg);
//	}
}
