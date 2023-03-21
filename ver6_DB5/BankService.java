package app.bankProject.ver6_DB5;

import java.io.IOException;
import java.sql.SQLException;

public class BankService {
	public static void main(String[] args) throws IOException {
		BankMenu bankGo = new BankMenu();
		DBmanagement dbm = new DBmanagement();
		try {
			bankGo.mainMenu();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			System.out.println("DB 연결 오류");
			e2.printStackTrace();
		} finally {
			try {
				dbm.closeDB();
				System.out.println("DB와의 연결을 닫습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
