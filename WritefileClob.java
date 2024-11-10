package bankdetails;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class WritefileClob {
	private static String url="jdbc:mysql://localhost:3306/emplyee";
	private static String un="root";
	private static String pw="root";
	static Connection con;
	private static String sql="update employee set doc=? where id=?";
	private static PreparedStatement pstmt;
	private static FileInputStream fis;

	private static BufferedReader br;

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
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(2, new Scanner(System.in).nextInt());
			//file input stream reads 1 byte ata time so use file reader stream it will take 2 bytes at a time so to fast up we use one more stream buffereader stream
			br=new BufferedReader(new FileReader("F:\\hello.txt"));
			pstmt.setCharacterStream(1, br);
			int x=pstmt.executeUpdate();
			System.out.println(x+"rows affected");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
