package firstjdbcapp;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class secondjdbc {
	static ResultSet resultSet;
	public static void main(String[]args) {
		String url="jdbc:mysql://localhost:3306/emplyee";
		String un="root";
		String pw="root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver is loaded");
			Connection con=DriverManager.getConnection(url,un,pw);
			System.out.println("connection"+con+"is established");

			Statement stmt=con.createStatement();
			resultSet =stmt.executeQuery("select*from emp");
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("id")+" "+
						resultSet.getString("name")+" "+resultSet.getString("email")+" "+resultSet.getInt("phone_num"));
			}

			;		}
		catch(ClassNotFoundException |SQLException e) {
			e.printStackTrace();

		}


	}

}
