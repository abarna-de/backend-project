package projo;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class Admin extends DBconnect{
	static int choice;
	static Scanner sc;
	static Statement smt;

	public void display() {
		System.out.println("welcome to Library Management System");


				}



	public static void admin() throws Exception {
		System.out.println("Enter 1 for Admin");
		System.out.println("Enter 2 for User");
		sc = new Scanner(System.in);  
		choice = sc.nextInt();
		switch(choice)
		{

		case 1:
			for(int i=0;i<4;i++)
			{
				if(i>=3)
				{
					System.out.println();
					System.out.println("Consicutive wrong atemps...");
					System.out.println();
					break;
				}
			System.out.print("Enter the user name : ");  
			String str1 = sc.next();  
			System.out.print("Enter the password : ");
			String str2 = sc.next();  
			ResultSet rs =smt.executeQuery("select * from usertable where name='" + str1 + "' and password='" + str2 + "'");
				if (rs.next())  
				{ 
					System.out.println("Welcome::: " + str1); 
					AdminSection.admindetails();

				}
					else  
				{
					System.out.println("Invalid user name and password");


				}
			}
				break;
		case 2:
			for(int i=0;i<3;i++)
			{
			System.out.print("Enter the user name : ");  
			String str3 = sc.next();  
			System.out.print("Enter the password : ");  
			String str4 = sc.next();  
			ResultSet rs1 =smt.executeQuery("select * from usertable where name='" + str3 + "' and password='" + str4 + "'");
				if (rs1.next())  
				{ 
					System.out.println("Welcome::: " + str3); 
					User.useraccess();

				}
					else  
				{
					System.out.println("Invalid user name and password"); 

				}

			}
			break;
			default:
				System.out.println("Invalid Input");
		}


	}

	public static void main(String[] args) throws Exception{
		Admin db=new Admin();
		db.connect();
		smt = db.getConnection().createStatement();
		con=db.getConnection();
		while(true)
		{
		db.display();
		db.admin();
		}
	 

	}

}
