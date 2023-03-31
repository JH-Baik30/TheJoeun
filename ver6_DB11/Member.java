package app.bankProject.ver6_DB11;

import java.text.DecimalFormat;

public class Member {
	private int account;
	private String id;
	private String pw;
	private String name;
	private String mobile;
	private String email;
	int balance;
	private String birth;

	DecimalFormat df = new DecimalFormat("###,###");
	
	public Member() {	}

	Member(int account, String id, String pw, String name, 
			String mobile, String email, String birth) {
		this.account = account;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.birth = birth;
	}
	
	public Member setAccount(int account)	{ this.account = account;	return this;}  
	public Member setId(String id) 			{ this.id = id;				return this;}
	public Member setPw(String pw)			{ this.pw = pw;				return this;}
	public void setMobile(String mobile) 	{ this.mobile = mobile;					}
	public void setEmail(String email) 		{ this.email = email;					}
	public Member setName(String name)		{ this.name = name;			return this;}
	public Member setBalance(int balance)	{ this.balance = balance;	return this;}
	public Member setBirth(String birth)	{ this.birth = birth;		return this;}

	public int getAccount()		{	return account;	}
	public String getId() 		{	return id;		}
	public String getPw() 		{	return pw;		}
	public String getMobile()	{	return mobile;	}
	public String getEmail() 	{	return email;	}
	public String getName()		{	return name;	}
	public int getbalance()		{	return balance;	}
	public String getBrith() 	{	return birth;	}

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
			return false;
		} else {
			balance -= withDraw;
			return true;
		}
	}
	
	// 이체 메소드
	public boolean transfer(int transferMoney) {
		if (balance >= transferMoney) {
			balance -= transferMoney;
			return true;
		} else {
			return false ;
		}
	}
	
	// 잔고 확인
	public int getBalance() {
		return balance;
	}
}
