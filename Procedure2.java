package Sql_Homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Procedure2 {

	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			//連接資料庫SQL
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=movie";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			
			//新增一筆資料到playlist
//			String insStmt = "insert into playlist values ('2016-12-25 13:00', 1, 'A廳');";
//			PreparedStatement pstmt1 = conn.prepareStatement(insStmt);
//			int s = pstmt1.executeUpdate();
//			System.out.println("成功更新筆數: " + s);
//			System.out.println("---------------------------------------");
			
			//查詢廳院座位表 m_room, 找出指定廳的座位數
//			String qryStmt = "Select seat_row,seat_col from m_room where roomid='A廳';";
//			PreparedStatement pstmt2 = conn.prepareStatement(qryStmt);
//			ResultSet rs = pstmt2.executeQuery();
//			while(rs.next()) {
//				System.out.println("seat_row" + rs.getInt(1));
//				System.out.println("eat_col" + rs.getInt(2));
//			}
//			System.out.println("---------------------------------------");
			
			//將該場次所有座位新增到 seats 表格 
			String insStmt = "{call gen_seats(?,?,?)}";
			PreparedStatement cstmt = conn.prepareStatement(insStmt);
			cstmt.setString(1, "2016-12-25 13:00");
			cstmt.setInt(2, 1);
			cstmt.setString(3, "A廳");
			if(cstmt.execute()){   
	            conn.rollback(); 
			}else{ 
				
			}
//			pstmt3.close();
//			rs.close();
//			pstmt2.close();
//			pstmt1.close();			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) { 
					e.printStackTrace();
				}
		}

	}

}
