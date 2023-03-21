package app.bankProject.ver6_DB5;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BankMenu {
	DBmanagement dbm = new DBmanagement();
//	ArrayList<Member> members;
	ArrayList<Member> members = dbm.readfromDB();
	int accNo;
	Scanner scanner = new Scanner(System.in);
	int idx;
	int cnt = 12300;

//	public void loadData() throws SQLException {
//		members = dbm.readfromDB();
//	}
	
	// 로그인
	public Member logIn() {				//////// 로그인할때 인덱스값을 같이 날릴까???
		System.out.print("계좌 번호를 입력하세요. ");
		int inputAcc = scanner.nextInt();
		System.out.print("비밀 번호를 입력하세요. ");
		String inputPw = scanner.next();
			for (int i = 0; i < members.size(); i++) {
				if (members.get(i).getAccount() == inputAcc) {
					if (members.get(i).getPw().equals(inputPw)) {
						idx = i;
						return members.get(i);
					} 
				}
			}
			System.out.println("계좌번호와 비밀번호를 확인하세요.");
			return null;
	}
	
	// 계정생성
	public void creatAccount() {
		accNo = members.get(members.size()-1).getAccount()+1;
		System.out.print("아이디를 입력하세요. ");
		String id = scanner.next();
		System.out.print("비밀번호를 입력하세요. ");
		String pw = scanner.next();
		System.out.print("이름을 입력하세요. ");
		String name = scanner.next();
		System.out.print("생성된 계좌 번호 " + accNo);
		members.add(new Member(accNo, id, pw, name));
		dbm.insertToDB(accNo, id, pw, name);
		members.get(members.size()-1).info();

		idx++;//		accNo++;
		System.out.println(name + " 님의 계좌가 생성 되었습니다.");
	}
	
	// 계좌이체
	public Member accountTransfer(int x) {
		Member targetMember = null;		// 이체 대상 선언
		System.out.println("이체할 대상의 계좌번호를 입력하세요. ");
		int targetAcc = scanner.nextInt();
		System.out.println("이체할 금액를 입력하세요. ");
		int transferMoney = scanner.nextInt();
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getAccount() == targetAcc) {
				targetMember = members.get(i);		// 이체 대상 객체 
				if (members.get(x-cnt).transfer(transferMoney)) {
					members.get(i).deposit(transferMoney);
					System.out.println(members.get(i).getName() + "님께 " + transferMoney + "원 송금완료");					
					dbm.updateToDB(targetMember.getAccount(), targetMember.balance);
					dbm.updateToDB(members.get(x-cnt).getAccount(), members.get(x-12300).balance);
					break;
				} 
				break;
			}
		}
		return members.get(x-cnt);
	}
	
	public void memberList() {
		for (Member mem : members) {
			System.out.println(mem.getAccount() + " , " + mem.getName());
		}
	}
	
	// 메인메뉴
	public void mainMenu() throws IOException, SQLException{
//		loadData();
		int choice = 0;
		boolean run = true;
		do {
			System.out.println();
			System.out.println("----------------------------------------------");
			System.out.println(" 1. 은행업무 | 2. 계좌생성 | 3. 회원 목록 | 4. 종료 ");
			System.out.println("----------------------------------------------");
			System.out.print("선택 > ");
			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
			}
			
			switch (choice) {
			case 1:
				Member member = logIn();
				if (member != null) {
					bankMenu(member);
				}
				break;
			case 2:
				creatAccount();
				break;
			case 3:
				memberList();
				break;
			case 4:
				dbm.closeDB();
				run = false;
				break;
			default:
				System.out.println("잘못입력했습니다");
			}
		} while (run);
	}
	// 은행업무 메뉴
	public Member bankMenu(Member loginuser) throws SQLException {
		boolean run = true;
		do {
			System.out.println();
			System.out.println(" " + loginuser.getName() + "님 로그인중 ");
			System.out.println("--------------------------------------------");
			System.out.println(" 1. 입금 | 2. 출금 | 3. 잔고 | 4. 이체 | 5. 종료 ");
			System.out.println("--------------------------------------------");
			System.out.print("선택 > ");

			int main = scanner.nextInt();

			switch (main) {
			case 1:
				System.out.println("입금할 금액을 입력하세요. ");
				int money = scanner.nextInt();
				loginuser.deposit(money);
				dbm.updateToDB(loginuser.getAccount(), loginuser.balance);
				break;
			case 2:
				System.out.println("출금할 금액을 입력하세요. ");
				money = scanner.nextInt();
				Boolean correct = loginuser.withDraw(money);
				if (correct) {
					dbm.updateToDB(loginuser.getAccount(), loginuser.balance);
				}
				break;
			case 3:
				loginuser.balance();
				break;
			case 4:
				accountTransfer(loginuser.getAccount());
				break;
			case 5:
				run = false;
				break;
				
			default:
				System.out.println("잘못입력했습니다.");
			}

		} while (run);
		return loginuser;
	}
}
