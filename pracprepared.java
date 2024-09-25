package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class pracprepared {
	static String url="jdbc:mysql://localhost:3306/emplyee";
	static String un="root";
	static String pw="root";
	static Connection con;
	static PreparedStatement pstmt;
	static ResultSet res;

	public static void main(String []args) {
		System.out.println("enter the id");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,un,pw);

			String query="Select*from emp where id=?";
			PreparedStatement pstmt=con.prepareStatement(query);
			Scanner scan=new Scanner(System.in);
			int id=scan.nextInt();
			pstmt.setInt(1, id);
			//			String  name=scan.next();
			//			System.out.println("enter name");
			//			pstmt.setString(2, name);
			ResultSet res=pstmt.executeQuery();
			if(res.next()) {
				System.out.println(res.getInt("id")+" "+res.getString(2)+" "+res.getString(3)+" "+res.getInt(4));
			}
			else {
				System.out.println("no record found");
			}



		}catch(ClassNotFoundException e) {
			e.printStackTrace();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		try{

			if(res!=null) {
				res.close();
				System.out.println("res closed");
			}
			if(res!=null) {
				pstmt.close();
				System.out.println("pstmt closed");

			}

			if(res!=null) {
				con.close();
				System.out.println("con closed");
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}