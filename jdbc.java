package firstjdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class jdbc {
	 	private static Connection con;
	 	static Statement stmt;
	 	static  ResultSet resultSet;
	public static void main(String []args) {
		String url="jdbc:mysql://localhost:3306/emplyee";
		String un="root";
		 String pw="root";
		 

		try {
			//loading the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			
			//Establishing the connection
			Connection con =DriverManager.getConnection(url,un,pw);
			//System.out.println("connection"+con+"is sucess");
			
			//creating the statement
			Statement stmt=con.createStatement();
			
			//execute sql query
			 resultSet =stmt.executeQuery("select*from emp");
			 
			 //process the data( res is pointing one data before  
			 while(resultSet.next()) {
				 System.out.println(resultSet.getInt(1)+ 
                    " "+resultSet.getString(2)+"  "+resultSet.getString(3)+" "+resultSet.getInt(4));
			 }	
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		finally {
			myconnect.connect(resultSet,stmt,con);
			
		}
		
		
		
		
		
	}

}
