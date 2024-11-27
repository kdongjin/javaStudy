package com.kh.subjectMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.subjectMVCProject.model.LessonVO;
import com.kh.subjectMVCProject.model.TraineeVO;

public class TraineeDAO {
	// query
	public final String TRAINEE_SELECT = "select * from TRAINEE";
	public final String TRAINEE_ALL_SELECT = "select T.no, T.section, T.regdate, S.num, S.name as sname, L.abbre, L.name as lname "
			+ " from trainee T inner join student S on T.s_num = S.num "
			+ " inner join lesson L on T.abbre = L.abbre order by T.no";
	public final String TRAINEE_SELECT_SORT = "select * from TRAINEE ORDER BY S_NUM";
	public final String TRAINEE_DELETE = "delete from TRAINEE where no = ?";
	public final String TRAINEE_UPDATE = "UPDATE TRAINEE SET S_NUM = ?, ABBRE = ?, SECTION = ? WHERE NO = ?";
	public final String TRAINEE_INSERT = "INSERT INTO TRAINEE VALUES(trainee_seq.NEXTVAL, ?, ?, ?,SYSDATE)";

	// TRAINEE 테이블에서 insert 레코드를 삽입한다. (insert)
	public boolean traineeInsert(TraineeVO tvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_INSERT);
			pstmt.setString(1, tvo.getS_num());
			pstmt.setString(2, tvo.getAbbre());
			pstmt.setString(3, tvo.getSection());

			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true) : (false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// traineeSelect
    public ArrayList<TraineeVO> traineeSelect(TraineeVO tvo) {
        Connection con = null; // 오라클 DB 접속하는 관문
        PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할수있게 해주는 명령문
        ResultSet rs = null; // 오라클에서 결과물을 받는객체
        ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>(); // 결과값을 다른객체에 전달하기 위해서 사용하는 객체

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(TRAINEE_SELECT);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("NO");
                String s_num = rs.getString("S_NUM");
                String abbre = rs.getString("ABBRE");
                String section = rs.getString("SECTION");
                Date regdate = rs.getDate("REGDATE");
                TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, regdate);
                traineeList.add(traineeVO);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            DBUtility.dbClose(con, pstmt, rs);
        }

        return traineeList;
    }

    
    // traineeAllSelect
    public ArrayList<TraineeVO> traineeAllSelect(TraineeVO tvo) {
        Connection con = null; // 오라클 DB 접속하는 관문
        PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할수있게 해주는 명령문
        ResultSet rs = null; // 오라클에서 결과물을 받는객체
        ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>(); // 결과값을 다른객체에 전달하기 위해서 사용하는 객체

        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(TRAINEE_ALL_SELECT);
            rs = pstmt.executeQuery();
//            T.no, T.section, T.regdate, S.num, S.name as sname, L.abbre, L.name as lname
            while (rs.next()) {
                int no = rs.getInt("NO");
                String section = rs.getString("SECTION");
                Date regdate = rs.getDate("REGDATE");
                String s_num = rs.getString("NUM");
                String s_name = rs.getString("SNAME");
                String abbre = rs.getString("ABBRE");
                String l_name = rs.getString("LNAME");
                TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, regdate, s_name, l_name);
                traineeList.add(traineeVO);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            DBUtility.dbClose(con, pstmt, rs);
        }
        return traineeList;
    }

	// trainee테이블에서 delete 레코드를 삭제한다. (delete)
	// 자동으로 커밋기능 셋팅이 되어있다.
	public boolean traineeDelete(TraineeVO tvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			// 커밋을 수동으로 바꾼다.
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(TRAINEE_DELETE);
			pstmt.setInt(1, tvo.getNo());
			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? true : false;
			if (count != 0) {
				successFlag = true;
				// 삭제성공시 커밋기능을 부여한다.
				con.commit();
			} else {
				successFlag = false;
				// 삭제실패시 롤백기능을 부여한다.
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// trainee 테이블에서 update 레코드를 수정한다. (update)
	public boolean traineeUpdate(TraineeVO tvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_UPDATE);
			pstmt.setString(1, tvo.getS_num());
			pstmt.setString(2, tvo.getAbbre());
			pstmt.setString(3, tvo.getSection());
			pstmt.setInt(4, tvo.getNo());

			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true) : (false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// trainee 테이블에서 select 출력레코드를 리턴한다. (Read)
	public ArrayList<TraineeVO> traineeSelectSort(TraineeVO lvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는객체
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>(); // 결과값을 다른객체전달하기 위해서 사용하는객체

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(TRAINEE_SELECT_SORT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String s_num = rs.getString("S_NUM");
				String abbre = rs.getString("ABBRE");
				String section = rs.getString("SECTION");
				Date regdate = rs.getDate("REGDATE");
				TraineeVO traineeVO = new TraineeVO(no, s_num, abbre, section, regdate);
				traineeList.add(traineeVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return traineeList;
	}

}
