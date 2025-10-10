package jdbc3.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbctest3 {

	public static void main(String[] args) {
		 String url ="jdbc:mariadb://localhost:3306/testdb";
	        String user = "testuser";
	        String pass = "testpass";
	        
	        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
	            System.out.println("✅ 연결 성공!");
	        } catch (Exception e) {
	            System.out.println("❌ 연결 실패");
	            e.printStackTrace();
	        }

	}

}
