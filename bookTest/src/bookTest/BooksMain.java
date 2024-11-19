package bookTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import oracle.jdbc.driver.DBConversion;

public class BooksMain {
	public static Scanner scan = new Scanner(System.in);
	public static final int PRINT = 1, INSERT = 2, UPDATE = 3, DELETE = 4, EXIT = 5;

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		// 사용자로부터 Books 출력, 입력, 수정, 삭제 , 종료요청받는다.
		while (!exitFlag) {
			printMenu();
			int num = Integer.parseInt(scan.nextLine());
			switch (num) {
			case PRINT:
				booksPrint();
				break;
			case INSERT:
				booksInsert();
				break;
			case UPDATE:
				booksUpdate();
				break;
			case DELETE:
				booksDelete();
				break;
			case EXIT:
				exitFlag = true;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
		}
		System.out.println("The end");
	}

	// 삭제
	private static void booksDelete() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1 Load,2 connect
		con = DBConnection.dbCon();
		// 3.statement
		System.out.print("삭제할번호>>");
		int no = Integer.parseInt(scan.nextLine());
		stmt = con.createStatement();
		int result = stmt.executeUpdate("DELETE FROM BOOKS WHERE ID = " + no);
		// 4.내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "삭제성공" : "삭제실패");
		// 6.sql 객체 반남
		DBConnection.dbClose(con, stmt);
	}

	// 수정
	private static void booksUpdate() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1 Load,2 connect
		con = DBConnection.dbCon();
		// 3.statement
		//수정할데이터를 입력
		Books books = new Books(3, "JAVA java", "kdj", "2024", 33000);
		
		stmt = con.createStatement();
		int result = stmt.executeUpdate("UPDATE BOOKS SET  TITLE = '"+
				books.getTitle()+"', PUBLISHER = '"+books.getPublisher()+"', YEAR = '"+
				books.getYear()+"' , PRICE = "+books.getPrice()+" WHERE ID = "+books.getId()+"");
		// 4.내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "수정성공" : "수정실패");
		// 6.sql 객체 반남
		DBConnection.dbClose(con, stmt);

	}

	// 삽입
	private static void booksInsert() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;

		// 1 Load,2 connect
		con = DBConnection.dbCon();
		// 3.statement
		Books books = new Books(0, "Head First JAVA", "kdj", "2008", 23000);
		String publisher = "kdj";
		stmt = con.createStatement();
		int result = stmt.executeUpdate("INSERT INTO books VALUES " + "(BOOKS_ID_SEQ.nextval,'" + books.getTitle()
				+ "','" + books.getPublisher() + "','" + books.getYear() + "'," + books.getPrice() + ")");
		// 4.내용이 잘 입력이 되었는지 check
		System.out.println((result != 0) ? "입력성공" : "입력실패");
		// 6.sql 객체 반남
		DBConnection.dbClose(con, stmt);

	}

	// 출력
	public static void booksPrint() throws SQLException {
		// Connection
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Books> booksList = new ArrayList<Books>();

		// 1 Load,2 connect
		con = DBConnection.dbCon();
		// 3.statement
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM  BOOKS");
		// 4.테이블내용 가져오기
		while (rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String publisher = rs.getString("PUBLISHER");
			String year = rs.getString("YEAR");
			int price = rs.getInt("PRICE");
			Books books = new Books(id, title, publisher, year, price);
			booksList.add(books);
		}
		// 5.출력하기
		booksListPrint(booksList);
		// 6.sql 객체 반남
		DBConnection.dbClose(con, stmt, rs);
	}

	private static void printMenu() {
		System.out.println("Books Menu(1.출력, 2.입력, 3.수정  4.삭제  5.종료)");
		System.out.print(">>");
	}

	private static void booksListPrint(ArrayList<Books> booksList) {
		for (Books books : booksList) {
			System.out.println(books.toString());
		}

	}

}
