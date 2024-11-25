package com.kh.subjectMVCProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.StudentAllVO;
import com.kh.subjectMVCProject.model.StudentVO;


public class StudentDAO {
		
	public static final String STUDENT_SELECT = "SELECT * FROM STUDENT";
    public static final String STUDENT_INSERT = "insert into student values(student_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
    public static final String STUDENT_CALL_RANK_PROC = "{call STUDENT_RANK_PROC()}";
    public static final String STUDENT_UPDATE = "UPDATE STUDENT SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE NO = ?";
    public static final String STUDENT_DELETE = "DELETE FROM STUDENT WHERE NO = ?";
    public static final String STUDENT_SORT = "SELECT *FROM STUDENT ORDER BY RANK";
    public static final String STUDENT_ID_CHECK = "select COUNT(*) AS COUNT from student where id = ?";
    public static final String STUDENT_NUM_COUNT ="select LPAD(count(*)+1,4,'0') as TOTAL_COUNT from student where s_num = ?";
	private static final String STUDENT_SUBJECT_JOIN_SELECT = "SELECT STU.NO, STU.NUM, STU.NAME, STU.ID,PASSWD,STU.S_NUM,SUB.NAME AS SUBJECT_NAME ,BIRTHDAY,PHONE,ADDRESS, EMAIL, SDATE\r\n"
			+ "FROM STUDENT STU INNER JOIN SUBJECT SUB ON STU.S_NUM = SUB.NUM ";
	
    public static ArrayList<StudentVO> studentSelect() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();
		con = DBUtility.dbCon();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(STUDENT_SELECT);
			if(rs.next()) {
				do{
					int no = rs.getInt("NO");
					String num = rs.getString("NUM");
					String name = rs.getString("NAME");
					String id = rs.getString("ID");
					String passwd = rs.getString("PASSWD");
					String s_num = rs.getString("S_NUM");
					String birthday = rs.getString("BIRTHDAY");
					String phone = rs.getString("PHONE");
					String address = rs.getString("ADDRESS");
					String email = rs.getString("EMAIL");
					Date sdate = rs.getDate("SDATE");
					StudentVO stu = new StudentVO(no, num, name, id, passwd, s_num, birthday, phone, address, email, sdate);
					studentList.add(stu);
				}while (rs.next());
			}else {
				studentList = null; 
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		return studentList;
	}
	
	public static boolean studentInsert(StudentVO svo) {
		// Conection
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(STUDENT_INSERT);
			pstmt.setString(1, svo.getNum());
			pstmt.setString(2, svo.getName());
			pstmt.setString(3, svo.getId());
			pstmt.setString(4, svo.getPasswd());
			pstmt.setString(5, svo.getS_num());
			pstmt.setString(6, svo.getBirthday());
			pstmt.setString(7, svo.getPhone());
			pstmt.setString(8, svo.getAddress());
			pstmt.setString(9, svo.getEmail());

			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag; 
	}

	public static boolean studentUpdate(StudentVO svo) throws SQLException {
		boolean successFlag = false; 
		Connection con = null;
		CallableStatement cstmt = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(STUDENT_UPDATE);
		pstmt.setString(1, svo.getName());
		

		int result1 = pstmt.executeUpdate();
		cstmt = con.prepareCall(STUDENT_CALL_RANK_PROC);
		int result2 = cstmt.executeUpdate();
		
		successFlag = (result1 != 0 && result2 != 0) ? true : false;

		DBUtility.dbClose(con, pstmt, cstmt);
		return successFlag; 
	}

	public static boolean studentDelete(StudentVO svo) throws SQLException {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(STUDENT_DELETE);
		pstmt.setInt(1, svo.getNo());
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false ;

		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}

	public static ArrayList<StudentVO> studentSort() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentVO> studentList = new ArrayList<StudentVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(STUDENT_SORT);

		if(rs.next()) {
			do {
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				int kor = rs.getInt("KOR");
				int eng = rs.getInt("ENG");
				int mat = rs.getInt("MAT");
				int total = rs.getInt("TOTAL");
				int ave = rs.getInt("AVE");
				int rank = rs.getInt("RANK");

				StudentVO stu = new StudentVO();
				studentList.add(stu);
			} while (rs.next());
		}else {
			studentList = null; 
		}

		DBUtility.dbClose(con, stmt, rs);
		return studentList; 
	}

	//중복아이디 체크
	public boolean studentIdCheck(StudentVO svo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0; 

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(STUDENT_ID_CHECK);
			pstmt.setString(1, svo.getId());
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				count = rs.getInt("COUNT");
			}else {
				count = 0; 
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return (count != 0)?(true):(false);
	}

	//해당 학과번호가져오기  01학과 2명있었다. 0003 리턴값을 준다.  
	public String getStudentCount(StudentVO svo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null; 

		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(STUDENT_NUM_COUNT);
			pstmt.setString(1, svo.getS_num());
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				result = rs.getString("TOTAL_COUNT");
			}else {
				result = null; 
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return result;
	}

	//해당학과와 학생정보를 조인해서 정보를 가져오기
	public ArrayList<StudentAllVO> studentAllSelect() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<StudentAllVO> studentAllList = new ArrayList<StudentAllVO>();
		con = DBUtility.dbCon();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(STUDENT_SUBJECT_JOIN_SELECT);
			if(rs.next()) {
				do{
					int no = rs.getInt("NO");
					String num = rs.getString("NUM");
					String name = rs.getString("NAME");
					String id = rs.getString("ID");
					String passwd = rs.getString("PASSWD");
					String s_num = rs.getString("S_NUM");
					String s_name = rs.getString("SUBJECT_NAME");
					String birthday = rs.getString("BIRTHDAY");
					String phone = rs.getString("PHONE");
					String address = rs.getString("ADDRESS");
					String email = rs.getString("EMAIL");
					Date sdate = rs.getDate("SDATE");
					StudentAllVO stu = 
							new StudentAllVO(no, num, name, id, passwd, s_num, s_name, birthday, phone, address, email, sdate);
					studentAllList.add(stu);
				}while (rs.next());
			}else {
				studentAllList = null; 
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		return studentAllList;
	}

}
