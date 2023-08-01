package cash.model;

import java.sql.*;

import cash.vo.Member;

public class MemberDao {
	public Member selectMemberById(Member paramMember) {
		Member returnMember = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select member_id memberId from member where member_id = ? and member_pw = password(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://52.79.53.122/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return returnMember;
	}
	
//  회원가입 메서드
  public int insertMember(Member member) {
     int row = 0;
     Connection conn = null;
     PreparedStatement stmt = null;
     String sql  = "INSERT INTO member SET member_id = ?, member_pw = PASSWORD(?), createdate = NOW(), updatedate = NOW()";
     try {
        String driver = "org.mariadb.jdbc.Driver";
        String dbUrl= "jdbc:mariadb://127.0.0.1:3306/cash";
        String dbUser = "root";
        String dbPw = "java1234";
        Class.forName(driver);
        conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, member.getMemberId());
        stmt.setString(2, member.getMemberPw());
        row = stmt.executeUpdate();
     } catch (Exception e1) {
        e1.printStackTrace();
     } finally {
        try {
        	//close()는 역순으로
           stmt.close();
           conn.close();
        } catch(Exception e2) {
           e2.printStackTrace();
        }
     }
     return row;
  }
  
  	//회원 탈퇴
	public int removeMember(String memberId, String memberPw) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	//회원 상세
	public Member selectMemberOne(String memberId) {
		Member returnMember = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId, member_pw memberPw FROM member WHERE member_id=?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
				System.out.println(stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
				returnMember.setMemberPw(rs.getString("memberPw"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{
				rs.close();
				stmt.close();
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return returnMember;
	}
  
}
