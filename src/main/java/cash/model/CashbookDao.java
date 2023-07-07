package cash.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.mariadb.jdbc.Statement;

import cash.vo.*;

public class CashbookDao {
	
	public List<Cashbook> selectCashbookListByTag(String memberId, String word, int beginRow, int rowPerPage){
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select c.* from cashbook c inner join hashtag h on c.cashbook_no = h.cashbook_no where c.member_id = ? and h.word = ? order by c.cashbook_date desc limit ?, ?";
		
		try {
			String driver = "org.mariadb.jdbc.Driver";
			String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
			String dbUser = "root";
			String dbPw = "java1234";
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, word);
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbook_no"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setCashbookDate(rs.getString("cashbook_date"));
				c.setMemo(rs.getString("memo"));
				list.add(c);
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
		return list;
	}
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth){
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select cashbook_no cashbookNo, category, price, cashbook_date cashbookDate from cashbook where member_id = ? and year(cashbook_date) = ? and month(cashbook_date) = ? order by cashbook_date asc";
		
		try {
			String driver = "org.mariadb.jdbc.Driver";
			String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
			String dbUser = "root";
			String dbPw = "java1234";
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				list.add(c);
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
		return list;
	}
	
	public List<Cashbook> selectCashbookListByDate(String memberId, int targetYear, int targetMonth, int targetDate){
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, price, memo, cashbook_date cashbookDate FROM cashbook WHERE member_id = ? and YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ? ORDER BY createdate desc";
		
		try {
			String driver = "org.mariadb.jdbc.Driver";
			String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
			String dbUser = "root";
			String dbPw = "java1234";
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, targetDate);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				list.add(c);
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
		return list;
	}
	
	   // 반환값 : cashbook_no 키값
	   public int insertCashbook(Cashbook cashbook) {
	      int cashbookNo = 0;
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null; // 입력후 생성된 키값 반환
	      try {
	         String driver = "org.mariadb.jdbc.Driver";
	         String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
	         String dbUser = "root";
	         String dbPw = "java1234";
	         Class.forName(driver);
	         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	         String sql = "INSERT INTO"
	               + " cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate)"
	               + " VALUES(?,?,?,?,?,NOW(),NOW())";
	         stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	         stmt.setString(1, cashbook.getMemberId());
	         stmt.setString(2, cashbook.getCategory());
	         stmt.setString(3, cashbook.getCashbookDate());
	         stmt.setInt(4, cashbook.getPrice());
	         stmt.setString(5, cashbook.getMemo());
	         
	         int row = stmt.executeUpdate();
	         rs = stmt.getGeneratedKeys();
	         if(rs.next()) {
	            cashbookNo = rs.getInt(1);
	         }
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            rs.close();
	            stmt.close();
	            conn.close();
	         }catch(Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	      return cashbookNo;
	   }
}
