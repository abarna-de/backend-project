package projo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class AdminSection extends DBconnect
{
	public static  void admindetails() throws Exception {
		int m;
		do
		{
			System.out.println("Enter 1 to Modifiy user");
			System.out.println("Enter 2 to Modify book");
			System.out.println("Enter 3 to Check issued details");
			System.out.println("Enter 4 to exit");
			System.out.println("Enter ur choice");
			Scanner sc = new Scanner(System.in); 
			m=sc.nextInt();
			switch(m)
			{
			case 1:
				Userdetails.adminuser();
				break;
			case 2:
				Book.book();
				break;
			case 3:
				Adminpaymentaccess.access();
				break;
			case 4:
				System.out.println("U have chosen to exit...");
				System.out.println("exited from the page  :)  ");
				Admin.admin();
				break;
			default:
				System.out.println("Enter choice from 1 - 4....!");
			}
		}while(m!=4);

	}
	}

