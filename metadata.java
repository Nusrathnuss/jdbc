package firstjdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class metadata {
	static String url="jdbc:mysql://localhost:3306/emplyee";
	static String un="root";
	static String pw="root";
	private static ResultSet res;
	private static ResultSetMetaData rsmd;
	public static void main(String []args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,un,pw);
			final String FETCH_ALL="Select*from emp";
			Statement stmt=con.createStatement();
			res=stmt.executeQuery(FETCH_ALL);
			rsmd=res.getMetaData();
			int count=rsmd.getColumnCount();
			for(int i=1;i<=count;i++) {
				System.out.println(rsmd.getColumnName(i)+" "+rsmd.getColumnTypeName(i));
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
