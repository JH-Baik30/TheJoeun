// DB control tester
package app.bankProject.ver6_DB4;

import java.util.ArrayList;

public class DBadmin {
public static void main(String[] args) throws Exception {
	DBmanagement dbm = new DBmanagement();
	int acc = 12300;
//	int[] arr = new int[10];
//	for (int i = 0; i < arr.length; i++) {
//		arr[i] = acc++; 
//	}
	String name = "aaa";
	String pw = "123";
//	dbm.insertToDB(acc, name, pw);
	
//	sql = "INSERT INTO BANKDB VALUES(1234, 'aaa', '123', 10, NOW())";
//	sql = "INSERT INTO DBTEST3 VALUES('bbb', '111', 10, NOW())";

//  앱 종료시 전체 객체를 DB 에 올리기 할때 반복문을 사용하면 마지막 값으로 전부 저장되버림.		
/*	앱 실행시 전체 DB 가져오기 TEST 중 문제!!!
 * 		DBDBmanagement.readfromDB() 에서 rfDB의 내용 자체를 못 받아온다.
 * 		아래 코드 실행시 반환값은
 * 		Result set representing update count of 10 * 
 */

/*	한번에 안들어감. ㅜㅜ
//	 DBmanagement test #1		INSERT method
//	dbm.insertToDB(acc,   "oneLove", "나도몰래", "한사랑");
//	Thread.sleep(2000);
//	dbm.insertToDB(acc+1, "java", "날자바", "김자바");
//	Thread.sleep(2000);
//	dbm.insertToDB(acc+2, "sping", "봄봄봄", "스프링");
//	Thread.sleep(2000);
//	dbm.insertToDB(acc+3, "python", "뱀이다", "파이썬");
//	Thread.sleep(2000);
//	dbm.insertToDB(acc+4, "sql", "마이마이", "에스큐엘");
*/
	
	// DBmanagement test #2		UPDATE method
//	dbm.updateToDB(12301, 100_000);
//	dbm.updateToDB();
	
/* 한번에 안지워짐. ㅠㅠ
	// DBmanagement test #3		DELETE method
//	dbm.deleteToDB(12300);
//	Thread.sleep(1000);
//	dbm.deleteToDB(12301);
//	Thread.sleep(1000);
//	dbm.deleteToDB(12302);
//	Thread.sleep(2000);
//	dbm.deleteToDB(12303);
//	dbm.deleteToDB(12304);
*/

	
	// DBmanagement test #1		insert method
	
	// DB에서 하나하나 가져오기 #1
/*
	ArrayList<Member> members = dbm.readfromDB();
	System.out.println(members.get(members.size()-1).getAccount()+1);
	for (int i = 0; i < members.size(); i++) {
		if (members != null) {
			System.out.println(members.get(i).getAccount()
					+ "\t"+ members.get(i).getId()
					+ "\t"+ members.get(i).getPw()
					+ "\t"+ members.get(i).getName()
					+ "\t"+ members.get(i).getbalance());
			}			
		} // #1 까지
*/	
	}
}
