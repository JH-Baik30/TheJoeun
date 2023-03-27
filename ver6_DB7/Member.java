package app.bankProject.ver6_DB7;

import java.text.DecimalFormat;

public class Member {
	//field
	private int account;
	private String id;
	private String pw;
	private String name;
	int balance;
	private String birth;

	DecimalFormat df = new DecimalFormat("###,###");
	
	Member(int account, String id, String pw, String name, String birth) {
		this.account = account;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
	}
	
	public Member() {
	}

	// getter, setter method
	public Member setAccount(int account)	{ this.account = account;	return this;}  
	public Member setId(String id) 			{ this.id = id;				return this;}
	public Member setPw(String pw)			{ this.pw = pw;				return this;}
	public Member setName(String name)		{ this.name = name;			return this;}
	public Member setBalance(int balance)	{ this.balance = balance;	return this;}
	public Member setBirth(String birth)		{ this.birth = birth;			return this;}

	public int getAccount() 		{	return account;	}
	public String getId() 			{	return id;		}
	public String getPw() 			{	return pw;		}
	public String getName() 		{	return name;	}
	public int getbalance() 		{	return balance;	}
	public String getBrith() 		{	return birth;	}
	
	public void info() {
		System.out.println(getName() + " 님의 계좌번호는 " + getAccount() + " 입니다.");
		System.out.println("패스워드는 " + getPw() + " 로 설정 되었습니다.");
	}
	
	// 입금 메소드
	public void deposit(int deposit) {
		balance += deposit;
	}
	
	// 출금 메소드
	public boolean withDraw(int withDraw) {
		if (withDraw > balance) {
			System.out.println("출금 가능액은 ₩ " + df.format(balance) + "원 입니다.");
			System.out.println("저축을 권장합니다.");
			return false;
		} else {
			balance -= withDraw;
			System.out.println("출금 후 잔액은 ₩ " + df.format(balance) + " 원입니다.");
//			balance();
			return true;
		}
	}
	
	// 이체 메소드
	public boolean transfer(int transferMoney) {
		if (balance >= transferMoney) {
			balance -= transferMoney;
			System.out.println(" ₩ " + df.format(transferMoney) + " 원을 이체하셨습니다.");
			return true;
		} else {
			System.out.println("요청 금액이 예금액보다 많습니다.");
			return false ;
		}
	}
	
	// 잔고 확인
	public void balance() {
		System.out.println("현재 예금액은 ₩ " + df.format(balance) + " 원 입니다.");
	}
}
