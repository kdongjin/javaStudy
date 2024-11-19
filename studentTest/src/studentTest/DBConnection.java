package studentTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {
	
	public static Connection dbCon() {
		// 객체참조변수 선언
		Connection con = null;

		// 1. jdbc driver load  2. connection
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe", "hr", "hr");
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		return con;
	}
	
	//sql 객체 close
	public static void dbClose(Connection con, Statement stmt, ResultSet rs) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}
	

}
