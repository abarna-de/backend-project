package projo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Userdetails extends DBconnect{
	  public static void adminuser() throws Exception 
	  {
	  	int n;
	  	Scanner sc = new Scanner(System.in);  
	  	do
	  	{
	  		System.out.println("Enter 1 to add user");
	  		System.out.println("Enter 2 to delete user");
	  		System.out.println("Enter 3 to search user");
	  		System.out.println("Enter 4 to view all users");
	  		System.out.println("Enter 5 to exit");
	  		System.out.println("Enter ur choice");
	  		n=sc.nextInt();
	  		String mail;
	  		int rows=0;
	  		switch(n)
	  		{
	  		case 1:
	  			String sql1 = "INSERT INTO usertable (name,u_age, u_email,password,u_Address,u_id, phone_number, u_type) VALUES (?, ?, ?, ?, ?, ?,?,?)";
	  			PreparedStatement statement1 = con.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
	  			System.out.println("Name");
	  			sc.nextLine();
	  			String name=sc.nextLine();
	  			System.out.println("Age");
	  			int age=sc.nextInt();
	  			System.out.println("Mail Id");
	  			boolean b = false;
	  			do
	  			{
	  			mail=sc.next();
	  			Pattern p=Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
	  			Matcher m=p.matcher(mail);
	  			b=m.matches();
	  				if(b==true)
	  				{
	  					System.out.println("Valid email");
	  					statement1.setString(3, mail);
	  			    }
	  				else
	  				{
	  					System.out.println("Invalid email... please enter the valid email");
	  					//mail=sc.next();
	  				}
	  			}while(b==false);
	  			System.out.println("Password");
	  			String pass=sc.next();
	  			System.out.println("Address");
	  			String address=sc.next();
	  			System.out.println("ID");
	  			int id=sc.nextInt();
	  			System.out.println("Phone number");
	  			String phonenumber=sc.next();
	  			System.out.println("User/Admin");
	  			String type=sc.next();
	  			statement1.setString(1, name);
	  			statement1.setInt(2, age);
	  			//statement1.setString(3, mail);
	  			statement1.setString(4, pass);	
	  			statement1.setString(5, address);
	  			statement1.setInt(6, id);
	  			statement1.setString(7, phonenumber);
	  			statement1.setString(8, type);	
	  			rows = statement1.executeUpdate();
	  			ResultSet res = statement1.getGeneratedKeys();
	  			while (res.next()) 
	  			{
	  				System.out.println("Library card number: "+res.getInt(1));
	  			}
	  			if(rows > 0)
	  			{
	  				System.out.println("1 row is inserted...!");
	  			}
	  			System.out.println("_______________________________________________");
	  			statement1.close();
	  			break;
	  		case 2:
	  			String sql2 = "DELETE FROM usertable WHERE (u_id = ?)";
	  			PreparedStatement statement2 = con.prepareStatement(sql2);
	  			System.out.println("Enter the id of the user to be DELETED from list");
	  			statement2.setInt(1, sc.nextInt());							
	  			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

	  			int row = statement2.executeUpdate();
	  			if(row > 0)
	  			{
	  				System.out.println("1 row is deleted...!");
	  			}
	  			else
	  				System.out.println("Sorry....! the row is empty!");
	  			System.out.println("_________________________________________________");
	  			statement2.close();
	  			break;
	  		case 3:
	  			 System.out.println("Enter the user id to view profile");
	  			 int a =sc.nextInt();
	  			 String sql3 = "select * from usertable WHERE u_id='"+a+"'";
	  			 Statement statement3 = con.createStatement();

	              ResultSet rs = statement3.executeQuery(sql3);
	              if(rs.next()==false) {
	             	 System.out.println("Sorry...There is no user available with the given id in the field");
	              }
	              else {
	  	             try {
	  	            	 do
	  	            	 {
	  	            	 	int b_id = rs.getInt("u_id");
	  						String b_name = rs.getString("name");
	  						String b_email = rs.getString("u_email");
	  						String b_password = rs.getString("password");
	  						String addre = rs.getString("u_Address");
	  						int b_phonenum = rs.getInt("phone_number");
	  						int b_libcard = rs.getInt("lcn");
	  						System.out.println("User id: "+b_id + "\nUser name: " + b_name + "\n email: " + b_email + "\nPhone number: " + b_phonenum + "\nAddress: " + addre + "\nPassword: " + b_password + "\nLibrary card number: " + b_libcard);
	  						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	  	            	 }while(rs.next());
	  	             }catch(SQLException e1)
	  	             {
	  	            	 System.out.println("sorry");
	  	             }
	              }
	  			 System.out.println("_________________________________________________________");
	              break;
	  		case 4:
	  			String sql4 = "SELECT * FROM usertable";
	  			Statement statement4 = con.createStatement();
	  			ResultSet result = statement4.executeQuery(sql4);

	  			int count=0;

	  			while(result.next())
	  			{
	  				int b_id = result.getInt("u_id");
	  				String b_name = result.getString("name");
	  				String b_email = result.getString("u_email");
	  				String b_password = result.getString("password");
	  				String addre = result.getString("u_Address");
	  				int b_phonenum = result.getInt("phone_number");
	  				int b_libcard =result.getInt("lcn");
	  				count++;
	  				System.out.println("Student id: "+b_id + "\nStudent name: " + b_name + "\nStudent email: " + b_email + "\nPhone number: " + b_phonenum + "\nAddress: " + addre + "\nPassword: " + b_password + "\nLibrary card number: " + b_libcard);
	  				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	          	}
	  			System.out.println("Total number of user:"+ count);
	  			System.out.println("______________________________________________________________");
	  			break;
	  		case 5:
	  			System.out.println("U have chosen to exit...");
	  			System.out.println("exited from the page  :) ");
	  			System.out.println("______________________________________________________________");	
	  			break;
	  		default:
	  			System.out.println("Enter choice from 1 - 5....!");

	  		}
	  	}while(n!=5);

	  }
	  }







