package firstjdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class crudjdbc {


	public static Connection con;
	public static ResultSet res;
	public Scanner scan=new Scanner(System.in);
	public Connection pstmt;

	static String url="jdbc:mysql://localhost:3306/emplyee";
	static String un="root";
	static String pw="root";
	void selectall() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/emplyee","root","root");
			String query="Select*from emp";
			Statement stmt=con.createStatement();
			res =stmt.executeQuery(query);
			while(res.next()) {
				System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getInt(4));
			}

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	void selectwithid() {
		System.out.println("enter the id to fetch the data");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,un,pw);

			String query="Select*from emp where id=?";
			PreparedStatement pstmt=con.prepareStatement(query);
			Scanner scan=new Scanner(System.in);
			int id=scan.nextInt();
			pstmt.setInt(1, id);

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
		finally {
			try{

				if(res!=null) {
					res.close();
					System.out.println("res closed");
				}
				if(pstmt!=null) {
					pstmt.close();
					System.out.println("pstmt closed");

				}

				if(con!=null) {
					con.close();
					System.out.println("con closed");
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	void insert() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,un,pw);

			String query="insert into emp(id,name,email,phone_num)values(?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(query);
			Scanner scan=new Scanner(System.in);
			System.out.println("enter id for inserting values in row");
			int id=scan.nextInt();
			pstmt.setInt(1, id);
			System.out.println("enter name");
			String name=scan.next();
			pstmt.setString(2, name);
			System.out.println("enter email");
			String email=scan.next();
			pstmt.setString(3, email);
			System.out.println("enter num");
			int phone_num=scan.nextInt();
			pstmt.setInt(4, phone_num);



			int x=pstmt.executeUpdate();
			System.out.println(x+"rows affected");

		}catch(ClassNotFoundException e) {
			e.printStackTrace();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	void update() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,un,pw);

			String query="update emp set name=?where id=?";
			PreparedStatement pstmt=con.prepareStatement(query);
			Scanner scan=new Scanner(System.in);


			System.out.println("enter name that you want to update");
			String name=scan.next();
			pstmt.setString(1, name);

			System.out.println("enter id for which you want to change");
			int id=scan.nextInt();
			pstmt.setInt(2, id);



			int x=pstmt.executeUpdate();
			System.out.println(x+"rows affected");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	void delete() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,un,pw);

			String query="delete from emp where id=?";
			PreparedStatement pstmt=con.prepareStatement(query);
			Scanner scan=new Scanner(System.in);	
			System.out.println("enter id for which you want to delete");
			int id=scan.nextInt();
			pstmt.setInt(1, id);
			int x=pstmt.executeUpdate();
			System.out.println(x+"rows affected");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public static void main(String []args) {
		proj p=new proj();
		p.selectall();
		p.selectwithid();
		p.insert();
		p.update();
		p.delete();

	}

}


