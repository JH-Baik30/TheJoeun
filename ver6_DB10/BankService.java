package app.bankProject.ver6_DB10;

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
				System.out.println("접속 종료");
				System.out.println("이용해 주셔서 감사합니다~❤️❤️❤️");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
