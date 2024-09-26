package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class update {
	static Connection con;
	public static void main(String []args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/emplyee","root","root");
			String updat_query="update emp set name=? ,email=? where id=?";
			PreparedStatement pstmt=con.prepareStatement(updat_query);

			Scanner scan=new Scanner(System.in);
			System.out.println("enter name");
			String name=scan.next();
			System.out.println("enter email");
			String email=scan.next();
			System.out.println("enter id");
			int id=scan.nextInt();

			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setInt(3, id);
			int x=pstmt.executeUpdate();
			System.out.println(x+"row updated");

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
