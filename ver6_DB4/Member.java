package app.bankProject.ver6_DB4;

public class Member {
	//field
	private int account;
	private String id;
	private String pw;
	private String name;
	int balance;
	
	Member(int account, String id, String pw, String name) {
		this.account = account;
		this.id = id;
		this.name = name;
		this.pw = pw;
	}
	
//	Member(int account, String name, String pw, int balance) {
//		this.account = account;
//		this.name = name;
//		this.pw = pw;
//		this.balance = balance;
//	}
	
	public Member() {
	}

	// getter, setter method
	public Member setAccount(int account)	{ this.account = account;	return this;}  
	public Member setPw(String pw)			{ this.pw = pw;				return this;}
	public Member setName(String name)		{ this.name = name;			return this;}
	public Member setBalance(int balance)	{ this.balance = balance;	return this;}
	public Member setId(String id) 			{ this.id = id;				return this;}

	public int getAccount() 		{	return account;	}
	public String getPw() 		{	return pw;		}
	public String getId() 		{	return id;	}
	public String getName() 		{	return name;		}
	public int getbalance() 		{	return balance;	}
	
	public void info() {
		System.out.println(getName() + " 님의 계좌번호는 " + getAccount() + " 입니다.");
		System.out.println("패스워드는 " + getPw() + " 로 설정 되었습니다.");
	}
	
	// 입금 메소드
	public void deposit(int deposit) {
		balance += deposit;
	}
	
	// 출금 메소드
	public void withDraw(int withDraw) {
		if (withDraw > balance) {
			System.out.println("요청 금액이 예금액보다 적습니다.");
		} else {
			balance -= withDraw;
			System.out.println(withDraw + " 원이 출금되었습니다.");
			balance();
		}
	}
	
	// 이체 메소드
	public void transfer(int transferMoney) {
		balance -= transferMoney;
	}
	
	// 잔고 확인
	public void balance() {
		System.out.println("현재 예금액은 " + balance + " 원입니다.");
	}
}
