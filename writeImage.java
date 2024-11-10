package bankdetails;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class writeImage {
	private static String url="jdbc:mysql://localhost:3306/emplyee";
	private static String un="root";
	private static String pw="root";
	static Connection con;
	private static String sql="update employee set photo=? where id=?";
	private static PreparedStatement pstmt;
	private static FileInputStream fis;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,un,pw);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//if u want to insert name  of file from user then write like this
		String name=new Scanner(System.in).next();
		System.out.println("enter id");
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(2, new Scanner(System.in).nextInt());
			fis=new FileInputStream("F:\\imagess\\"+name+".png");
			pstmt.setBinaryStream(1, fis);
			int x=pstmt.executeUpdate();
			System.out.println(x+"rows updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		//		try {
		//			
		//			pstmt=con.prepareStatement(sql);
		//			System.out.println("enter the id");
		//			//to avoid memory wastage we can also write like this that creting object and simultaneously collecting integer data
		//			//int id=new Scanner(System.in).nextInt();
		//			//instead of writing like this just write where ever u need it to avoid extra usage of memory
		//			pstmt.setInt(2, new Scanner(System.in).nextInt());
		//			//create obj of name fileinpstream
		//			//here double slash is one it will take escape so
		//			fis=new FileInputStream("F:\\imagess\\tim.png");
		//			pstmt.setBinaryStream(1, fis);
		//			int x=pstmt.executeUpdate();
		//			System.out.println(x+"rows affected");
		//		}catch(Exception e) {
		//			e.printStackTrace();
		//		}	// TODO Auto-generated method stub
	}
}
