package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utill.DBConn;
import vo.Attach;
import vo.Board;
import vo.Criteria;

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
	
	public void writeAttach(Attach attach) {
		Connection connection = DBConn.getConnection();
		
		try {
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TBL_ATTACH VALUES (?, ?, ?, ?)");
			int idx=1;
			pstmt.setString(idx++, attach.getUuid());
			pstmt.setString(idx++, attach.getOrigin());
			pstmt.setLong(idx++, attach.getBno());
			pstmt.setString(idx++, attach.getPath());
			
			pstmt.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Attach> readAttachByPath(String path) {
		Connection connection = DBConn.getConnection();
		List<Attach> list = new ArrayList<Attach>();
		
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT UUID, ORIGIN, BNO, PATH FROM TBL_ATTACH WHERE PATH = ?");
			pstmt.setString(1, path);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx = 1;
				String uuid = rs.getString(idx++);
				String origin = rs.getString(idx++);
				
				Attach attach = new Attach(uuid, origin, null, path);
				list.add(attach);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//첨부파일 목록만
	public List<Attach> readAttach(Long bno) {
		Connection connection = DBConn.getConnection();
		List<Attach> list = new ArrayList<Attach>();
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT UUID, ORIGIN, PATH FROM TBL_ATTACH WHERE BNO =?");
			pstmt.setLong(1, bno);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx=1;
				String uuid = rs.getString(idx++);
				String origin = rs.getString(idx++);
				String path = rs.getString(idx++);
				
				Attach attach = new Attach(uuid, origin, bno, path);
				list.add(attach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String findByOriginBy(String uuid) {
		Connection connection = DBConn.getConnection();
		String origin = null;
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT ORIGIN FROM TBL_ATTACH WHERE UUID= ? ");
			pstmt.setString(1, uuid);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				origin = rs.getString(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return origin;
	}
	
	public Long insert(Board board) {
		Connection connection = DBConn.getConnection();
		Long bno = null;
		try {
			connection.setAutoCommit(false);
			
			ResultSet rs = connection.prepareStatement("SELECT SEQ_BOARD.NEXTVAL FROM DUAL").executeQuery();
			rs.next();
			bno=rs.getLong(1);
			
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TBL_BOARD (BNO, TITLE, CONTENT, WRITER) VALUES (?, ?, ?, ?)");
			int idx = 1;
			pstmt.setLong(idx++, bno);
			pstmt.setString(idx++, board.getTitle());
			pstmt.setString(idx, board.getContent());
			pstmt.setString(idx++, board.getWriter());
			
			pstmt.executeQuery();
			connection.commit();
			connection.setAutoCommit(true);
		} catch(SQLException e) {
			e.printStackTrace();
		} return bno;
	}
	
	public int getCount() {
		Connection connection = DBConn.getConnection();
		int count=0;
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) FROM TBL_BOARD");
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public int getCount(Criteria cri) {
		Connection connection = DBConn.getConnection();
		int count=0;
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) FROM TBL_BOARD");
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Board> list() {
		Connection connection = DBConn.getConnection();
		List<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("SELECT BNO, TITLE, CONTENT, WRITER, REGDATE FROM TBL_BOARD ORDER BY BNO DESC");
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
	
	public List<Board> list(Criteria cri) {
		Connection connection = DBConn.getConnection();
		List<Board> list = new ArrayList<Board>();
		try {
			Statement stmt = connection.createStatement();
			
			PreparedStatement pstmt = connection.prepareStatement(
					"WITH B AS (\r\n" + 
					"SELECT ROWNUM RN, bno, title,  regDate, writer, content\r\n" + 
					"FROM TBL_BOARD\r\n" + 
					"WHERE BNO >0\r\n" + 
					"AND ROWNUM <= ? * ?\r\n" + 
					"ORDER BY BNO DESC)\r\n" + 
					"SELECT BNO, TITLE, REGDATE, writer, CONTENT \r\n" + 
					"FROM B\r\n" + 
					"WHERE RN > (? - 1) * ?\r\n"
					);
		
			int idx = 1;
			pstmt.setInt(idx++, cri.getPageNum());
			pstmt.setInt(idx++, cri.getAmount());
			pstmt.setInt(idx++, cri.getPageNum());
			pstmt.setInt(idx++, cri.getAmount());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idx = 1;
				Long bno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				Date regDate = rs.getDate(idx++);
				String writer = rs.getString(idx++);
				
				Board board = new Board(bno, title, regDate, writer);
				board.setContent(rs.getString(idx++));
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("리스트 출력성공");
		
		return list;
	}
	
	public static void main(String[] args) {
		BoardDao dao = new BoardDao();
		
		List<Board> list = dao.list(new Criteria(2, 20));
		for(Board m : list) {
			System.out.println(m);
		}
		System.out.println(dao.getCount());
		
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
