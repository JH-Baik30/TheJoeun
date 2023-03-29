package app.bankProject.ver6_DB10;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class FUI extends JFrame {
	private JPanel contentPane;
	private JTextField tfID, tfPw, tfIptID, tfName, tfBirth;
	private JPasswordField pfPw, pfRePw;
	private JButton btnIDChk;
	private CardLayout card;
	private JTextArea tfYnbank;
	private JTextField tfMobile, tfEmail;
	DBmanagement dbm = new DBmanagement();
	DecimalFormat df = new DecimalFormat("###,###");
	ArrayList<Member> members = dbm.readfromDB();
	Member currentLogInMember = null;
	private JTextField ipIpFd;
	JButton btnTransfer;
	
	public FUI() {
		setTitle("YN Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		card = new CardLayout();
		setContentPane(contentPane);
		contentPane.setLayout(card);
		
// logIn page -------------------------------------------------------
		JPanel login = new JPanel();
		login.setBorder(UIManager.getBorder("Button.border"));
		contentPane.add(login, "login");
		login.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		login.add(panel);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel frontDoor = new JPanel();
		panel.add(frontDoor);
		
		JLabel lblNewLabel_19 = new JLabel();
		frontDoor.add(lblNewLabel_19);
		
		JPanel idPw = new JPanel();
		panel.add(idPw);
		idPw.setLayout(new BorderLayout(0, 0));
		JLabel lbsp1 = new JLabel("");
		idPw.add(lbsp1, BorderLayout.NORTH);
		JLabel lbsp2 = new JLabel("");
		idPw.add(lbsp2, BorderLayout.SOUTH);
		JLabel lbsp3 = new JLabel("                          ");
		idPw.add(lbsp3, BorderLayout.WEST);
		JLabel lbsp4 = new JLabel("                          ");
		idPw.add(lbsp4, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		idPw.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(3, 0, 0, 0));
		
// 로그인 아이디 받기
		tfID = new JTextField();
		tfID.setText("아이디");
		tfID.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (tfID.getText().equals("")) tfID.setText("아이디");	}	
			@Override
			public void focusGained(FocusEvent e) {
				tfID.setText("");			}		});
		
		panel_3.add(tfID);
		tfID.setColumns(20);
		
		JLabel lbsp5 = new JLabel("                     ");
		panel_3.add(lbsp5);
		
// 로그인 비밀번호 받기
		tfPw = new JTextField();
		tfPw.setText("비밀번호");
		tfPw.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (tfPw.getText().equals("")) tfPw.setText("비밀번호");}
			@Override
			public void focusGained(FocusEvent e) {
				tfPw.setText("");		}		});
		
		panel_3.add(tfPw);
		tfPw.setColumns(20);
		
		JPanel buttons = new JPanel();
		panel.add(buttons);
		buttons.setLayout(new BorderLayout(0, 0));
		JLabel lbsp6 = new JLabel("                          ");
		buttons.add(lbsp6, BorderLayout.NORTH);
		JLabel lbsp7 = new JLabel("                          ");
		buttons.add(lbsp7, BorderLayout.WEST);
		JLabel lbsp8 = new JLabel("                          ");
		buttons.add(lbsp8, BorderLayout.SOUTH);
		JLabel lbsp9 = new JLabel("                          ");
		buttons.add(lbsp9, BorderLayout.EAST);
		JPanel panel_4 = new JPanel();
		buttons.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(1, 2, 0, 0));
		
// 로그인 버튼		
		JButton btnNewButton = new JButton("로그인");
		panel_4.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfID.getText();
				String pw = tfPw.getText();
				if (logIn(id, pw) != null) {
					currentLogInMember = logIn(id, pw);
					card.show(contentPane, "banking");
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("회원가입");
		panel_4.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "membership");
			}
		});
		
// membership page  -------------------------------------------------
		JPanel membership = new JPanel();
		contentPane.add(membership, "membership");
		membership.setLayout(new BorderLayout(0, 0));
		
		JLabel lbsp10 = new JLabel("                      ");
		membership.add(lbsp10, BorderLayout.WEST);
		
		JLabel lbsp11 = new JLabel("                      ");
		membership.add(lbsp11, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		membership.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(9, 2, 0, 0));
		
		JLabel lbID = new JLabel("아이디 입력");
		lbID.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbID);
		
		JLabel lbsp12 = new JLabel("       ");
		panel_2.add(lbsp12);
		
		tfIptID = new JTextField();				// 입력받은 아이디
		panel_2.add(tfIptID);
		tfIptID.setColumns(10);
		
// 아이디 중복체크		
		btnIDChk = new JButton("중복확인");
		panel_2.add(btnIDChk);
		btnIDChk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(idCheck(tfIptID.getText())) {
					btnIDChk.setText("중복아이디");
				} else {
					btnIDChk.setText("사용가능");
					tfIptID.setEditable(false);			}
			}		});
		
		JLabel lbPw = new JLabel("비밀번호");			// 입력받은 password
		lbPw.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbPw);
		
		JLabel lbRePw = new JLabel("비밀번호 확인");	// password check
		lbRePw.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbRePw);
		
		pfPw = new JPasswordField();
		panel_2.add(pfPw);
		
		pfRePw = new JPasswordField();
		panel_2.add(pfRePw);
		
		JLabel lbName = new JLabel("이    름");
		lbName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbName);
		
		JLabel lbBirth = new JLabel("생 년 월 일");
		lbBirth.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbBirth);
		
		tfName = new JTextField();			// 이름 입력
		panel_2.add(tfName);
		tfName.setColumns(10);
		
		tfBirth = new JTextField();			// 생일 입력
		panel_2.add(tfBirth);
		tfBirth.setColumns(10);
		
		JLabel lbMobile = new JLabel("휴대폰번호");
		lbMobile.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbMobile);
		
		JLabel lbEmail = new JLabel("email");
		lbEmail.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lbEmail);
		
		tfMobile = new JTextField();			// 폰번호
		panel_2.add(tfMobile);
		tfMobile.setColumns(10);
		
		tfEmail = new JTextField();			// 이멜 
		panel_2.add(tfEmail);
		tfEmail.setColumns(10);
		
// 회원가입 버튼
		JButton btnNewMember = new JButton("가    입");
		panel_2.add(btnNewMember);
		btnNewMember.addActionListener(e -> {
			if (creatAccount(tfIptID.getText(), pfPw.getPassword(), tfName.getText(),
					tfMobile.getText(), tfEmail.getText(), tfBirth.getText())) {
				card.show(contentPane, "welcome");
			} else {
				tfID.setText("아이디");
				tfPw.setText("비밀번호");				
			}	}	);
		
		JButton btnReset = new JButton("초 기 화");
		panel_2.add(btnReset);
		btnReset.addActionListener(e ->{
			tfIptID.setText(""); pfPw.setText(""); tfName.setText(""); pfRePw.setText("");
			tfMobile.setText(""); tfEmail.setText(""); tfBirth.setText("");
			tfIptID.setEditable(true);
		});
		
		JLabel lbsp13 = new JLabel("  ");		membership.add(lbsp13, BorderLayout.NORTH);
		JLabel lbsp14 = new JLabel("  ");		membership.add(lbsp14, BorderLayout.SOUTH);
		
// banking page -----------------------------------------------------
		JPanel banking = new JPanel();
		contentPane.add(banking, "banking");
		banking.setLayout(new BorderLayout(0, 0));
		
		JLabel lbsp15 = new JLabel("    ");		banking.add(lbsp15, BorderLayout.NORTH);
		JLabel lbsp16 = new JLabel("    ");		banking.add(lbsp16, BorderLayout.WEST);
		JLabel lbsp17 = new JLabel("    ");		banking.add(lbsp17, BorderLayout.EAST);
		JLabel lbsp18 = new JLabel("    ");		banking.add(lbsp18, BorderLayout.SOUTH);
		
		JPanel panel_5 = new JPanel();
		JPanel panel_6 = new JPanel();
		banking.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
// 뱅킹 좌측 패널
		panel_5.add(panel_6, BorderLayout.WEST);
		panel_6.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_5.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		tfYnbank = new JTextArea();
		tfYnbank.setText("안녕하세요. YNBANK 입니다.");
		
		tfYnbank.setColumns(10);
		panel_1.add(tfYnbank);
		
		ipIpFd = new JTextField();
		panel_1.add(ipIpFd, BorderLayout.SOUTH);
		ipIpFd.setColumns(10);
		
		JButton btnDeposit = new JButton("   입금   ");
		panel_6.add(btnDeposit);
		btnDeposit.addActionListener(e -> {
			try {
				int depo;
				if (ipIpFd.getText() != null) {
					depo = Integer.parseInt(ipIpFd.getText());
					deposit(currentLogInMember, depo);
					ipIpFd.setText("");
				} else {
					tfYnbank.append("입금할 금액을 입력하세요");
				}	} catch (Exception e2) {			}
		} );
		
		JButton btnWithdraw = new JButton("   출금   ");
		panel_6.add(btnWithdraw);
		btnWithdraw.addActionListener(e -> {
			try {
				int depo;
				if (ipIpFd.getText() != null) {
					depo = Integer.parseInt(ipIpFd.getText());
					withdraw(currentLogInMember, depo);
					ipIpFd.setText("");
				} else {
					tfYnbank.append("출금할 금액을 입력하세요");
				}	} catch (Exception e2) {			}
		} );

//**********************************************************************
		
		btnTransfer = new JButton("   이체   ");
		panel_6.add(btnTransfer);
		btnTransfer.addActionListener(e -> {
			accountTransfer(currentLogInMember);
		});
		
//**********************************************************************
		
// 뱅킹 우측 패널		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.EAST);
		panel_7.setLayout(new GridLayout(3, 0, 0, 0));
		
		JButton bntTransactionList = new JButton("예금조회");
		panel_7.add(bntTransactionList);
		bntTransactionList.addActionListener(e -> {
			balanceCheck(currentLogInMember);
		});
		
		JButton bntTransactionEnd = new JButton("거래종료");
		panel_7.add(bntTransactionEnd);
		bntTransactionEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "login");
				tfID.setText("아이디");
				tfPw.setText("비밀번호");
			}		});
		
		JButton bntInfoMody = new JButton("정보수정");
		bntInfoMody.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_7.add(bntInfoMody);
		
// (❁´◡`❁) welcome page -----------------------------------------------------
		JPanel welcome = new JPanel() {
			Image bgi = new ImageIcon(FUI.class.getResource(
							"welcome1.jpg")).getImage();
			public void paint(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bgi, 0, 0, getWidth(), getHeight(), this);
			}
		};
		
		JButton button = new JButton("확인");
		welcome.setLayout(new BorderLayout());
		welcome.add(button, "Center");
		
		welcome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(contentPane, "login");
			}
		});
		
		contentPane.add(welcome, "welcome");
		welcome.setLayout(new BorderLayout(0, 0));
		
		setVisible(true);
	}

// (❁´◡`❁) 로그인
	public Member logIn(String id, String pw) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(id)) {
				if (members.get(i).getPw().equals(pw)) {

					return members.get(i);	}	}	}
		tfID.setText("아이디를 확인하세요.");
		tfPw.setText("비밀번호를 확인하세요.");
		return null;
	}
	
// (❁´◡`❁) 중복 아이디 체크
	public boolean idCheck(String id) {
		for (Member mem : members) {
			String getid = tfIptID.getText().trim();
			if (mem.getId().equals(getid)) {	return true;	} 
		}	return false;	}
	
// 계정 생성	
	public boolean creatAccount(String ID, char[] cpw, String Name,
							String  Mobile, String  Email, String Birth) {
		int accNo = members.get(members.size()-1).getAccount();
		accNo += (int)(Math.random()*100);
		String pw = String.valueOf(cpw);
		members.add(new Member(accNo, ID, pw, Name, Mobile, Email, Birth));
		System.out.println(1);
		dbm.insertToDB(accNo, ID, pw, Name, Mobile, Email, Birth);
		return true;		}
	public Date strToDate(String stringDate) {
		LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
		Date sqlDate = Date.valueOf(date);
//		System.out.println(date);
		return sqlDate;
	}
	
// (❁´◡`❁) 입금
	public Member deposit(Member loginUser, int money) {
		loginUser.deposit(money);
		tfYnbank.append("\n ₩ " + df.format(money) + " 입금완료");
		dbm.depositLog(loginUser.getAccount(),
				loginUser.getId(), money);
		dbm.updateToDB(loginUser.getAccount(), loginUser.balance);
		return loginUser;
	}
	
// (❁´◡`❁) 출금
	public Member withdraw(Member loginUser, int money) {
		Boolean correct = loginUser.withDraw(money);
		if (correct) {
			dbm.withDrawLog(loginUser.getAccount(),
					loginUser.getId(), money);
			dbm.updateToDB(loginUser.getAccount(), loginUser.balance);
			tfYnbank.append("\n ₩ " + df.format(money) + " 출금완료");
			tfYnbank.append("\n 출금후 잔액은");
			tfYnbank.append("\n ₩ " + df.format(loginUser.balance) + "원 입니다. \n");
		}
		return loginUser;
	}
	
// 계좌이체
	public Member accountTransfer(Member logInMember) {
		String input1 = JOptionPane.showInputDialog(null, "상대 계좌번호 입력");
		String input2 = JOptionPane.showInputDialog(null, "송금할 금액 입력 ");
		int targetAccount = Integer.parseInt(input1);
		int transferMoney = Integer.parseInt(input2);
		Member targetMember = null;		// 이체 대상 선언
		
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getAccount() == targetAccount) {
				targetMember = members.get(i);					// 이체 대상 객체 
				if (logInMember.transfer(transferMoney)) {		// 계좌 이체 가능 유무 확인
					targetMember.deposit(transferMoney);			// 대상의 계좌에 송금
					tfYnbank.append("\n\n " + targetMember.getName() + "님께 ");
					tfYnbank.append("\n ₩ "+ df.format(transferMoney) + "원 송금완료");
//					System.out.println(members.get(i).getName() + "님께  ₩ " + df.format(transferMoney) + "원 송금완료");					
//					이체 당사자 출금 기록 loginMember
					dbm.updateToDB(logInMember.getAccount(), logInMember.balance);
					dbm.withDrawLog(logInMember.getAccount(),
									logInMember.getId(),
									transferMoney,
									targetMember.getName());
//					이체 대상자 입금 기록 targetMember
					dbm.updateToDB(targetMember.getAccount(), targetMember.balance);
					dbm.depositLog(targetMember.getAccount(),
								   targetMember.getId(), 
								   transferMoney,
								   logInMember.getName() );
					break;
				} 
				break;
			}
		}
		return null;
	}
	
// 잔액조회
	public String balanceCheck(Member currentLogInMember ) {
		String blnc = String.valueOf(df.format(currentLogInMember.getbalance()));
		tfYnbank.append("\n\n" + currentLogInMember.getName() + "님의 통장 잔액은");
		tfYnbank.append("\n잔액은 ₩ " + blnc + "원입니다.");
		return blnc;
	}
	
	public static void main(String[] args) {
//		new UItest01();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FUI frame = new FUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}
