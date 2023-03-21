//	Create Read Update Delete
package app.bankProject.ver6_DB5;

import java.sql.Connection;
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
//				if(conn != null) conn.close();		// 연결을 끊는다.
			} catch (Exception e2) {	}
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
			}
			
			return mem;
		} catch (Exception e) {
			System.out.println("데이터베이스 드라이버 로딩및 연결실패!");
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();		// 상태를 닫고
//				if(conn != null) conn.close();		// 연결을 끊는다.
			} catch (Exception e2) {	}
		}
		return null;
	}
	
//	ACCOUNT, ID, PW, NAME, Balance
//	👍 test pass 👍
	public void insertToDB(int account, String id, String pw, String name) {
		String sql = "INSERT INTO BANKDB VALUES('"
					+ account + "', '" + id + "', '"
					+ pw + "', '" + name + "', "
					+ 0 + ", NOW())";
		msg = controlDB(sql);
		System.out.println(name + "님의 계좌 개설에 " + msg + " 했습니다.");
	}
	
//	👍 test pass 👍	
	public void updateToDB(int account, int balance) {
		String sql = "UPDATE BANKDB SET BALANCE=" + balance
					+ " WHERE ACCOUNT="+ account;
//		System.out.println("DB 로드에 " + msg + " 했습니다.");
		msg = controlDB(sql);
//		System.out.println(account + "계좌에 " + balance + " 원이 입금되었습니다.");
	}
	
//	👍 test pass 👍
	public void deleteToDB(int account) {
		String sql = "DELETE FROM BANKDB WHERE Account='"+ account + "'";
		msg = controlDB(sql);
//		System.out.println("DB 삭제 " + msg + " 했습니다.");
	}
	
	public void deleteToDB(String id) {
		String sql = "DELETE FROM BANKDB WHERE id='"+ id + "'";
		msg = controlDB(sql);
//		System.out.println("DB 삭제 " + msg + " 했습니다.");
	}
	
	public void closeDB() throws SQLException {
		if(conn != null) conn.close();
	}
	
// DBadmin 에서 test 용으로 사용하는 메소드
	public void sqlTestMethod(String sql) {
//		String sql = "INSERT INTO BANKDB VALUES(aaa, '123', 10, NOW())";
		msg = controlDB(sql);
		System.out.println("작업 결과 : " + msg);
	}
}
