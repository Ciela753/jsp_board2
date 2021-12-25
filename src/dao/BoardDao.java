package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConn;
import vo.Board;

public class BoardDao {
	// 목록
	// List X
	// 상세
	// Board bno
	// 작성
	// void Board
	// 수정
	// void Board
	// 삭제
	// void bno
	
	public List<Board> list() {
		Connection connection = DBConn.getConnection();
		List<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("SELECT * FROM TBL_BOARD ORDER BY BNO DESC");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int cIndx = 1;
				list.add(new Board(rs.getLong(cIndx++), rs.getString(cIndx++), rs.getString(cIndx++), rs.getString(cIndx++), rs.getDate(cIndx++)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public Board get(Long bno) {
		Connection connection = DBConn.getConnection();
		Board board=null;
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("SELECT BNO, TITLE, WRITER, CONTENT, REGDATE FROM TBL_BOARD WHERE BNO= ?");
			pstmt.setLong(1, bno);
			
			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("상세조회 쿼리 실행 성공");
			while(resultSet.next()) {
				int columnIndex=1;
				board = new Board(
						resultSet.getLong(columnIndex++),
						resultSet.getString(columnIndex++),
						resultSet.getString(columnIndex++),
						resultSet.getString(columnIndex++),
						resultSet.getDate(columnIndex++)
						);
				System.out.println("상세조회 쿼리 입력 성공");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return board;
	}
	public void register(Board board) {
		Connection connection = DBConn.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("INSERT INTO TBL_BOARD (BNO, TITLE, CONTENT, WRITER, REGDATE) VALUES (SEQ_BOARD.NEXTVAL, ?, ?, ?, SYSDATE)");
			int parameterIndex=1;
			pstmt.setString(parameterIndex++, board.getTitle());
			pstmt.setString(parameterIndex++, board.getContent());
			pstmt.setString(parameterIndex++, board.getWriter());
			
			pstmt.executeQuery();
			System.out.println("글등록 성공");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void modify(Board board) {
		Connection connection = DBConn.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("UPDATE TBL_BOARD SET TITLE= ?, CONTENT= ? WHERE BNO= ?");
			int parameterIndex=1;
			pstmt.setString(parameterIndex++, board.getTitle());
			pstmt.setString(parameterIndex++, board.getContent());
			pstmt.setLong(parameterIndex++, board.getBno());
			
			pstmt.executeQuery();
			System.out.println("글 수정 성공");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void remove(Long bno) {
		Connection connection = DBConn.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("DELETE FROM TBL_BOARD WHERE BNO=?");
			int parameterIndex=1;
			pstmt.setLong(parameterIndex++, bno);
			
			pstmt.executeQuery();
			System.out.println("글 삭제 성공");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
