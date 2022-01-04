package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConn;
import vo.Member;

public class MemberDao {
	public List<Member> getMembers() {
		Connection connection = DBConn.getConnection();
		List<Member> list = new ArrayList<Member>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, PWD, EMAIL, NAME FROM TBL_MEMBER");
			
			while(rs.next()) {
				String id=rs.getString("ID");
				String pwd=rs.getString("pwd");
				String email=rs.getString("email");
				String name=rs.getString("name");
				
				Member member = new Member(id, pwd, email, name);
				
				list.add(member);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean login(String id, String pwd) {
		Connection connection = DBConn.getConnection();
		boolean success = false;
		
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT ID, PWD, EMAIL, NAME FROM TBL_MEMBER WHERE ID=? AND PWD=?");
			int idx=1;
			pstmt.setString(idx++, id);
			pstmt.setString(idx++, pwd);
			ResultSet rs = pstmt.executeQuery();
			
			success = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public void join(Member member) {
		Connection connection = DBConn.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement
					("INSERT INTO TBL_MEMBER(ID, PWD, EMAIL, NAME) VALUES (?, ?, ?, ?)");
			int parameterIndex = 1;
			pstmt.setString(parameterIndex++, member.getId());
			pstmt.setString(parameterIndex++, member.getPwd());
			pstmt.setString(parameterIndex++, member.getEmail());
			pstmt.setString(parameterIndex++, member.getName());
			
			pstmt.executeQuery();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Member findBy(String id) {
		Connection connection = DBConn.getConnection();
		Member member = null;
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT ID, PWD, EMAIL, NAME FROM TBL_MEMBER WHERE ID=?");
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int idx=1;
				member = new Member(
						rs.getString(idx++), 
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++)
						);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
}
