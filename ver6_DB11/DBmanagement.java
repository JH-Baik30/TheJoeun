//	Create Read Update Delete
package app.bankProject.ver6_DB11;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBmanagement {
	Connection conn = DBAction.getInstance().getConnection();	
	Statement stmt = null;
	ResultSet rs = null;
	private String msg;
	ArrayList<Member> mem = new ArrayList<>();
	ArrayList<memTran> memTrans = new ArrayList<>();
	
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
			String sql = "SELECT * FROM ynbank0331 ORDER BY ACCOUNT ASC";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				mem.add(new Member().setAccount(rs.getInt("account"))
									.setId(rs.getString("id"))
									.setPw(rs.getString("pw"))
									.setName(rs.getString("name"))
									.setBalance(rs.getInt("balance"))
									.setBirth(rs.getString("birth_day")));
			}
			
			return mem;
		} catch (Exception e) {
			System.out.println("데이터베이스 드라이버 로딩및 연결실패!");
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();		// 상태를 닫고
			} catch (Exception e2) {	}				// 연결 끊는 메소드는 아래에.
		}
		return null;
	}
	
//	👍 test pass 👍
	public void insertToDB(int account, String id, String pw, String name,
								String mobile, String email, String birth) {
		LocalDate date = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
		String sql = "INSERT INTO ynbank0331 VALUES("
					+ account + ", '" + id + "', '" + pw + "', '"
					+ name + "', '" + mobile + "', '" + email + "', "
					+ 0 + ", NOW(), '" + date + "')";
		msg = controlDB(sql);
	}
	
//	👍 test pass 👍	
	public void updateToDB(int account, int balance) {
		String sql = "UPDATE ynbank0331 SET BALANCE=" + balance
					+ " WHERE ACCOUNT="+ account;
		msg = controlDB(sql);
	}

//	입금
	public void depositLog(int account, String id, int deposit) {
		String sql = "insert into ynbank0331transactiondb(ACCOUNT, ID, DEPOSIT, TRANSACTION_DATE)"
			+ " values (" + account +", '" + id + "', " + deposit + ", NOW())";
		controlDB(sql);			//에러
	}
	
// 이체용 입금
	public void depositLog(int account, String id, int deposit, String name) {
		String sql = "insert into ynbank0331transactiondb(ACCOUNT, ID, DEPOSIT, SUBJECT, TRANSACTION_DATE)"
				+ " values (" + account +", '" + id + "', " + deposit + ", '" + name + "', NOW())";
		controlDB(sql);
	}
	
//	출금
	public void withDrawLog(int account, String id, int WITH_DRAW) {
		String sql = "insert into ynbank0331transactiondb(ACCOUNT, ID, WITH_DRAW, TRANSACTION_DATE)"
				+ " values (" + account +", '" + id + "', " + WITH_DRAW + ", NOW())";
		controlDB(sql);			//에러
	}
	
//	이체용 출금
	public void withDrawLog(int account, String id, int TRANSFER, String name) {
		String sql = "insert into ynbank0331transactiondb(ACCOUNT, ID, TRANSFER, SUBJECT, TRANSACTION_DATE)"
				+ " values (" + account +", '" + id + "', " + TRANSFER + ", '" + name +  "', NOW())";
		controlDB(sql);
	}
	
//	거래 내역--------------------------------------------------------------------------
	public ArrayList<memTran> transaction(int account, String id) {
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM ynbank0331transactiondb WHERE "
					+ "ACCOUNT=" + account +" && "
					+ "ID='" + id + "' ORDER BY TRANSACTION_DATE DESC";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				memTrans.add(new memTran().setAccount(rs.getInt("ACCOUNT"))
										 .setWith_draw(rs.getInt("with_draw"))
										 .setDeposit(rs.getInt("DEPOSIT"))
										 .setTransfer(rs.getInt("TRANSFER"))
										 .setSubject(rs.getString("SUBJECT"))
										 .setTranDate(rs.getString("TRANSACTION_DATE")));
				}
			return memTrans;
		} catch (Exception e) {
			System.out.println("데이터베이스 드라이버 로딩및 연결실패!");
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();			// 상태를 닫고
			} catch (Exception e2) {	}					// 연결 끊는 메소드는 아래에.
		}
		return null;	}
	
	public void closeDB() throws SQLException {
		if(conn != null) conn.close();
	}
}
