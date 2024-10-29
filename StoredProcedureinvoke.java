package bankdetails;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class StoredProcedureinvoke {
	
		static private Statement stmt;
		static private ResultSet res;
		private static Connection con;
		private static Scanner scan=new Scanner(System.in);
		static String url="jdbc:mysql://Localhost:3306/emplyee";
		static String un="root";
		static String pw="root";
		private static PreparedStatement pstmt;
		private static CallableStatement callablestmt;

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
		public static void main(String []args) {
			String sql= "{call based_on_salary(?,?)}";
			System.out.println("enter the salary");
			int sal=scan.nextInt();
			try {
				callablestmt=con.prepareCall(sql);
				callablestmt.setInt(1, sal);
				callablestmt.registerOutParameter(2, Types.INTEGER);
			callablestmt.execute();
			int x=callablestmt.getInt(2);
			System.out.println(x);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
