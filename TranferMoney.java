package bankdetails;
//ACID PROPERTIES   (atomicity-any data transaction is should be 100percnt success or 100percent failure should no have 50% 
//to get data from one consistent state to another consistent state is consistency
//consistency- balance is before 1000 then 200 is transfered and reciver has 1000 before total amount before tranfer is 2000 and after also 2000 (total amount )
//isolation -the transaction should be isolated from all other transaction should not affect others
//durability-details should be durable if shop is present or not data should present ex:plane crashes shop)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TranferMoney {
	private static final String  query="Select*from user";
	private static final String S_AMOUNT="update user set balance=balance-? where accountno=? ";
	private	static final String R_AMOUNT="update user set balance=balance+? where accountno=?";

	static private Statement stmt;
	static private ResultSet res;
	private static Connection con;
	private static Scanner scan=new Scanner(System.in);
	static String url="jdbc:mysql://Localhost:3306/bank";
	static String un="root";
	static String pw="root";
	private static PreparedStatement pstmt;

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
		disp();
		//for automicity set it as false after reciver data is entered then it should be commit() because at starting only we dont want to deduct amount from sender withour any receiver)
		//commit() then rollback() for automicity use this 3 things
		
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("enter saccno");
		String saccno=scan.next();
		System.out.println("enter the amount");
		String  amount=scan.next();
		try {
			pstmt=	con.prepareStatement(S_AMOUNT);
			pstmt.setString(1,amount);
			pstmt.setString(2, saccno);
			int x=pstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("enter receivr acno");
		String racountno=scan.next();
		try {
			pstmt=con.prepareStatement(R_AMOUNT);


			pstmt.setString(1, amount);
			pstmt.setString(2, racountno);
			int x=pstmt.executeUpdate();
//			if(x==0) 
//				System.out.println("Failure");
//			else
//				System.out.println("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//before commiting it should check some authentication like pin so create one class authenticate
//		try {
//			con.commit();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		Authenticate.verify(scan,con);
		disp();

	}



	static void disp() {
		try {
			stmt=con.createStatement();
			res=stmt.executeQuery(query);
			while(res.next()) {
				System.out.println(res.getInt(1)+" "+res.getInt(2)+" "+res.getString(3)+" "+res.getInt(4));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
