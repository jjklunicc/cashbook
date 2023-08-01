package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cash.vo.Hashtag;

public class HashtagDao {
	public List<Map<String, Object>> selectWordCountByMonth(String memberId, int targetYear, int targetMonth){
		List<Map<String,Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	     
		try {
			String driver = "org.mariadb.jdbc.Driver";
			String dbUrl = "jdbc:mariadb://52.79.53.122/cash";
			String dbUser = "root";
			String dbPw = "java1234";
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			String sql = "SELECT word, COUNT(*) cnt \r\n"
					+ "FROM hashtag h INNER JOIN cashbook c\r\n"
					+ "ON h.cashbook_no = c.cashbook_no\r\n"
					+ "WHERE c.member_id = ?\r\n"
					+ "and YEAR(c.cashbook_date) = ? AND MONTH(c.cashbook_date) = ?\r\n"
					+ "GROUP BY word         \r\n"
					+ "ORDER BY COUNT(*) DESC";
			
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, memberId);
	         stmt.setInt(2, targetYear);
	         stmt.setInt(3, targetMonth);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	        	 Map<String, Object> m = new HashMap<>();
	        	 m.put("word", rs.getString("word"));
	        	 m.put("cnt", rs.getInt("cnt"));
	        	 list.add(m);
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
		
		return list;
	}
   public int insertHashtag(Hashtag hashtag) {
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      int row = 0;
	      try {
	         String driver = "org.mariadb.jdbc.Driver";
	         String dbUrl = "jdbc:mariadb://127.0.0.1:3306/cash";
	         String dbUser = "root";
	         String dbPw = "java1234";
	         Class.forName(driver);
	         conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
	         String sql = "INSERT INTO hashtag(cashbook_no, word, updatedate, createdate)"
	                  + " VALUES(?,?,NOW(),NOW())";
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, hashtag.getCashbookNo());
	         stmt.setString(2, hashtag.getWord());
	         row = stmt.executeUpdate();
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            stmt.close();
	            conn.close();
	         }catch(Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	      return row;
	   }
}
