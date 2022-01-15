package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utill.DBConn;
import vo.Reply;

public class ReplyDao {
	public void insert(Reply reply) {
		Connection connection = DBConn.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"INSERT INTO TBL_REPLY(RNO, TITLE, CONTENT, ID, BNO) VALUES (SEQ_REPLY.NEXTVAL, ?, ?, ?, ?)");
			int idx=1;
			pstmt.setString(idx++, reply.getTitle());
			pstmt.setString(idx++, reply.getContent());
			pstmt.setString(idx++, reply.getId());
			pstmt.setLong(idx++, reply.getBno());
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Reply> list(Long bno) {
		Connection connection = DBConn.getConnection();
		List<Reply> list = new ArrayList<Reply>();
		
		try {
			String sql = "SELECT RNO, TITLE, CONTENT, TO_CHAR(REGDATE, 'YY/MM/DD') AS REGDATE, ID, BNO\r\n"
					+ "FROM TBL_REPLY tr WHERE RNO > 0 AND BNO = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx = 1;
				Long rno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				String content = rs.getString(idx++);
				String regDate = rs.getString(idx++);
				String id = rs.getString(idx++);
				
				Reply reply = new Reply(rno, title, content, regDate, id, bno);
				list.add(reply);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Reply select(Long rno) {
		Connection connection = DBConn.getConnection();
		Reply reply = null;
		try {
			String sql = "SELECT RNO, TITLE, CONTENT, TO_CHAR(REGDATE, 'YY/MM/DD') AS REGDATE, ID, BNO FROM TBL_REPLY WHERE RNO = ?";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, rno);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int idx = 1;
				rno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				String content = rs.getString(idx++);
				String regDate = rs.getString(idx++);
				String id = rs.getString(idx++);
				Long bno = rs.getLong(idx++);
				
				reply = new Reply(rno, title, content, regDate, id, bno);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reply;
	}
	public void delete(Long rno) {
		Connection connection = DBConn.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement("DELETE TBL_REPLY WHERE RNO = ?");
			int idx = 1;
			pstmt.setLong(idx++, rno);
			
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
