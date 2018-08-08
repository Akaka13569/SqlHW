package Sql_Homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Procedure1 {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			//�s����ƮwSQL
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=movie";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			
			//�s�W�@����ƨ�playlist
			String insStmt = "insert into playlist values ('2016-12-25 13:00', 1, 'A�U');";
			PreparedStatement pstmt1 = conn.prepareStatement(insStmt);
			int s = pstmt1.executeUpdate();
			System.out.println("���\��s����: " + s);
			System.out.println("---------------------------------------");
			
			//�d���U�|�y��� m_room, ��X���w�U���y���
			String qryStmt = "Select seat_row,seat_col from m_room where roomid='A�U';";
			PreparedStatement pstmt2 = conn.prepareStatement(qryStmt);
			ResultSet rs = pstmt2.executeQuery();
			while(rs.next()) {
				System.out.println("seat_row" + rs.getInt(1));
				System.out.println("eat_col" + rs.getInt(2));
			}
			System.out.println("---------------------------------------");
			
			//�N�ӳ����Ҧ��y��s�W�� seats ��� 
			String insStmt2 = "insert into seats values (?, ?, ?, ?, ?);";
			PreparedStatement pstmt3 = conn.prepareStatement(insStmt2);
			for(int i = 1;i <= 25; i++) { //�y�� 1-ij
				for(int j = 1;j <= 20; j++) { //�y�� 1-ij
					String q = String.format("%02d", i); //format��k ("1",2) 1���Y����� �Ʀr�e����0 (%0) ���o�Ӧr��X�Ӧr (2)
					String q1 = String.format("%02d", j);
					pstmt3.setString(1, "2016-12-25 13:00");
					pstmt3.setInt(2, 1);
					pstmt3.setString(3,q + "-" + q1);
					pstmt3.setInt(4, 0);
					pstmt3.setObject(5, null);
					pstmt3.execute();
				}
			}
			
			pstmt3.close();
			rs.close();
			pstmt2.close();
			pstmt1.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) { 
					e.printStackTrace();
				}
		}

	}

}
