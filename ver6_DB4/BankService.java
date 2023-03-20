package app.bankProject.ver6_DB4;

import java.io.IOException;
import java.sql.SQLException;

public class BankService {
	public static void main(String[] args) throws InterruptedException, SQLException {
		BankMenu bankGo = new BankMenu();
		try {
			bankGo.mainnMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
}
