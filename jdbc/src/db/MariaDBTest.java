package db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MariaDBTest {

    public static void main(String[] args) {
        String url ="jdbc:mariadb://localhost:3306/testdb";
        String user = "testuser";
        String pass = "testpass";
        
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            System.out.println("✅ 연결 성공!");

            // ===== 1. MariaDB 버전 확인 =====
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT VERSION() AS v")) {
                if (rs.next()) {
                    System.out.println("📌 MariaDB 버전: " + rs.getString("v"));
                }
            }

            // ===== 2. 테이블 생성 =====
//            try (Statement stmt = conn.createStatement()) {
//                String createSql = "CREATE TABLE IF NOT EXISTS users (" +
//                                   "id INT AUTO_INCREMENT PRIMARY KEY, " +
//                                   "name VARCHAR(50), " +
//                                   "email VARCHAR(100))";
//                stmt.executeUpdate(createSql);
//                System.out.println("📌 users 테이블 준비 완료");
//            }

            // ===== 3. 데이터 INSERT =====
//            String insertSql = "INSERT INTO users (name, email) VALUES (?, ?)";
//            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
//                pstmt.setString(1, "이순신");
//                pstmt.setString(2, "lee@test.com");
//                pstmt.executeUpdate();
//                System.out.println("✅ 데이터 삽입 완료");
//            }

            // ===== 4. 데이터 SELECT =====
            String selectSql = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSql)) {
                while (rs.next()) {
                    System.out.println("▶ 조회 결과: " +
                        rs.getInt("id") + " / " +
                        rs.getString("name") + " / " +
                        rs.getString("email"));
                }
            }
            // ===== UPDATE (Statement 사용) =====
//            try (Statement stmt = conn.createStatement()) {
//                String updateSql = "Update users set name='홍길동', email='hong@test.com' where id=1";
//                int rows = stmt.executeUpdate(updateSql);  // 영향받은 행 수 리턴
//                System.out.println("🔄 업데이트된 행 수: " + rows);
//            }
            
            // ===== DELETE (Statement 사용) =====
            try (Statement stmt = conn.createStatement()){
            	String deleteSql = "delete from users where id = 1";
            	int deleted = stmt.executeUpdate(deleteSql);
            	System.out.println("🗑 삭제된 행 수: " + deleted);
            }
            String selectSql1 = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSql1)) {
                while (rs.next()) {
                    System.out.println("▶ 조회 결과: " +
                        rs.getInt("id") + " / " +
                        rs.getString("name") + " / " +
                        rs.getString("email"));
                }
            }
          

        } catch (Exception e) {
            System.out.println("❌ 연결 실패");
            e.printStackTrace();
        }
    }
}
