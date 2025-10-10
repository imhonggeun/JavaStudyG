package jdbc2;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MariaDB {

	public static void main(String[] args) {
		String url = "jdbc:mariadb://localhost:3306/testdb";
		String a = "testuser";
		String b = "testpass";
		
		try(Connection conn = DriverManager.getConnection(url,a,b)){
			System.out.println("연결 확인");
			
			
	          //추가
//          String insertSql = "INSERT INTO users (name, email) VALUES (?, ?)";
//          try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
//              pstmt.setString(1, "이순신");
//              pstmt.setString(2, "lee@test.com");
//              pstmt.executeUpdate();
//              System.out.println("✅ 데이터 삽입 완료");
//          }
          // try(Statement stmt = conn.createStatement()){
          //	  String updateSql = "UPDATE users SET NAME='이순신', email='lee@test.com' WHERE id=2;";
		  //		int rows = stmt.executeUpdate(updateSql);
		  //		System.out.println("update 성공 : "+rows);
          //}
          
          String selectSql = "select * from users";
          try(Statement stmt = conn.createStatement();
        		  ResultSet rs = stmt.executeQuery(selectSql)){
        	  while(rs.next()) {
        		  System.out.println("조회결과 :"+rs.getInt("id")+ "/" + rs.getString("name") + "/" + rs.getString("email"));
        	  }
          }
          
			
		} catch (SQLException e) {
			System.out.println("연결실패");
			e.printStackTrace();
			
		}
	}

}
