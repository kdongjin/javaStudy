package publicDataTest.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import publicDataTest.model.LandPriceVO;


public class LandPriceDAO {
		
	public  final String LANDPRICE_SELECT = "SELECT * FROM LANDPRICE";
	public  final String LANDPRICE_CHECK_NODENO_SELECT = "SELECT count(*) AS COUNT FROM LANDPRICE WHERE NODENO = ?";
    public  final String LANDPRICE_INSERT = "insert into LANDPRICE values(?, ?, ?, ?, ?)";
    public  final String LANDPRICE_UPDATE = "UPDATE LANDPRICE SET GPSLATI = ?, GPSLONG = ?, NODEID = ?, NODENM = ? WHERE NODENO = ?";
    public  final String LANDPRICE_DELETE = "DELETE FROM LANDPRICE WHERE NODENO = ?";
	
	public ArrayList<LandPriceVO> landPriceSelect() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<LandPriceVO> list = new ArrayList<LandPriceVO>();

		con = DBUtility.dbCon();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(LANDPRICE_SELECT);

			if(rs.next()) {
				do{
					int nodeno = rs.getInt("nodeno");
					double gpslati = rs.getDouble("gpslati");
					double gpslong = rs.getDouble("gpslong");
					String nodeid = rs.getString("nodeid");
					String nodenm = rs.getString("nodenm");
					LandPriceVO lvo = new LandPriceVO(nodeno, gpslati, gpslong, nodeid, nodenm);
					list.add(lvo);
				}while (rs.next());
			}else {
				list = null; 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		return list;
	}
	
	public boolean landPriceInsert(LandPriceVO lvo) throws SQLException  {
		//Conection
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(LANDPRICE_INSERT);
		pstmt.setInt(1, lvo.getNodeno());
		pstmt.setDouble(2, lvo.getGpslati());
		pstmt.setDouble(3, lvo.getGpslong());
		pstmt.setString(4, lvo.getNodeid());
		pstmt.setString(5, lvo.getNodenm());
		
		int result = pstmt.executeUpdate();
		
		DBUtility.dbClose(con, pstmt);
		successFlag = (result != 0 ) ? true : false;
		return successFlag; 
	}

	public boolean landPriceUpdate(LandPriceVO lvo) {
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LANDPRICE_UPDATE);
			pstmt.setDouble(1, lvo.getGpslati());
			pstmt.setDouble(2, lvo.getGpslong());
			pstmt.setString(3, lvo.getNodeid());
			pstmt.setString(4, lvo.getNodenm());
			pstmt.setInt(5, lvo.getNodeno());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0 ) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag; 
	}

	public boolean landPriceDelete(LandPriceVO  lvo)  {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LANDPRICE_DELETE);
			pstmt.setInt(1, lvo.getNodeno());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false ;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag; 
	}


	public int landPriceCheckNodeNOSelect(LandPriceVO lvo) {
		Connection con = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		ArrayList<LandPriceVO> list = new ArrayList<LandPriceVO>();
		int count = 0; 
		
		try {
		con = DBUtility.dbCon();
			pstmt = con.prepareStatement(LANDPRICE_CHECK_NODENO_SELECT);
			pstmt.setInt(1, lvo.getNodeno());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		
		return count;
	}

}
