package mavenjdbc;

import java.sql.*;

public class db {

	public static void main(String[] args) {
		
		//DB연결 여부 확인
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("JDBC 드라이버가 성곡적으로 로드되었습니다");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버를 로드하는데 실패했습니다.");
			e.printStackTrace();
		}
		
		//mariadb 연결
		String url = "jdbc:mariadb://localhost:3306/testdb";
		String user = "testuser";
		String pass = "testpass";
		
		String insertSql = "INSERT INTO USERS(name,email) VALUES(?,?)";
		String updateSql = "UPDATE USERS SET name=?,email=? where id=?";
		String deleteSql = "DELETE FROM USERS where id=?";
		String selectSql = "SELECT * FROM USERS";
		
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			//insert
			 try(PreparedStatement ps = conn.prepareStatement(insertSql)){
				 ps.setString(1,"홍길동"); 
				 ps.setString(2, "hong@test.com"); 
				 int insert = ps.executeUpdate();
				 System.out.println(insert + "row insert"); 
			 }
			
			
			//update
			try(PreparedStatement ps = conn.prepareStatement(updateSql)){
				ps.setString(1, "변경");
				ps.setString(2, "change@test.com");
				ps.setLong(3, 5L);
				int updated = ps.executeUpdate();
				System.out.println(updated + "row updated");
			}
			//delete 
			try(PreparedStatement ps = conn.prepareStatement(deleteSql)){
				ps.setLong(1, 7L);
				int deleted = ps.executeUpdate();
				System.out.println(deleted + "row deleted");
			}
			
			//select
			try(PreparedStatement ps = conn.prepareStatement(selectSql);
					ResultSet rs = ps.executeQuery()){
				while(rs.next()){
					System.out.printf("%d %s %s%n",
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("email"));
				}
			}
		} catch (SQLException e) {
			System.out.println("sql 실패");
			e.printStackTrace();
		}
	}

}