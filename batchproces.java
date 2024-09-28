package firstjdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class batchproces {
	static String url="jdbc:mysql://localhost:3306/emplyee";
	static String un="root";
	static String pw="root";
	public static void main(String []args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,un,pw);
			String query="insert into emp (id,name,email,phone_num)values(?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(query);
			Scanner scan=new Scanner(System.in);
			Scanner scan2=new Scanner(System.in);

			for(int i=1;i<=5;i++) {
				System.out.println("enter id");
				int id=scan.nextInt();
				pstmt.setInt(1, id);

				System.out.println("enter name");
				String name=scan2.next();
				pstmt.setString(2, name);

				System.out.println("enter email");
				String email=scan2.next();
				pstmt.setString(3, email);

				System.out.println("enter num");
				int phone_num=scan.nextInt();
				pstmt.setInt(4,phone_num);

				pstmt.addBatch();
				System.out.println(i+"query is added to batch");
			}
			pstmt.executeBatch();
			System.out.println("batch executed");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
