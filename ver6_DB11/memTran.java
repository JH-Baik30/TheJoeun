package app.bankProject.ver6_DB11;

public class memTran {
	private int account;
	private int with_draw;
	private int deposit;
	private int transfer;
	private String subject;
	private String tranDate;
	
	public memTran() { }
	
	public memTran setAccount(int account)		{	this.account = account;		return this;	}
	public memTran setWith_draw(int with_draw)	{	this.with_draw = with_draw;	return this;	}
	public memTran setDeposit(int deposit)		{	this.deposit = deposit;		return this;	}
	public memTran setTransfer(int transfer)		{	this.transfer = transfer;	return this;	}
	public memTran setSubject(String subject)	{	this.subject = subject;		return this;	}
	public memTran setTranDate(String tranDate)	{	this.tranDate = tranDate;	return this;	}

	public int getAccount()		{	return account;		}
	public int getWith_draw()	{	return with_draw;	}
	public int getDeposit()		{	return deposit;		}
	public int getTransfer()		{	return transfer;		}
	public String getSubject()	{	return subject;		}
	public String getTranDate()	{	return tranDate;		}
}
