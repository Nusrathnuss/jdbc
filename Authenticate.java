package bankdetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Authenticate {
	private static  int pin=121212;
	public static void verify(Scanner scan,Connection con) {
		System.out.println("do u really want to transfer money?\n then enter upin");
		int upin=scan.nextInt();
		//here it is not entering into the and one more thing is if u enter the wrong upin then it should goes to previous one
		//data limit is full then we will enter the data the whole data will crash use rollback() to go to savepoint exp:watsapp,twitter it has savepoints
		if(pin==upin) {
			System.out.println("inside if");
			try {
				con.commit();
				System.out.println("commited");
			} 
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
		else {
			try {
				con.rollback();
				System.out.println("rollback");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
