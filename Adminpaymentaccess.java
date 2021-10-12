package projo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Adminpaymentaccess extends DBconnect{
	static void access() throws Exception {


		int m1;
		Scanner sc = new Scanner(System.in); 

		do {

			System.out.println("Enter 1 to return book");
			System.out.println("Enter 2 to price amount and fine");
			System.out.println("Enter 3 to view issued book");
			System.out.println("Enter 4 to exit");
			System.out.println("Enter ur choice");
			m1=sc.nextInt();
			switch(m1)
			{
			case 1:

				String sql2 = "DELETE FROM ISSUED WHERE (issued_id = ?)";
				PreparedStatement statement2 = con.prepareStatement(sql2);
				System.out.println("Enter Issued ID");
				statement2.setString(1, sc.next());

				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				int row = statement2.executeUpdate();
				if(row > 0)
				{
					System.out.println("1 row is deleted...!");
				}
				else
				System.out.println("Sorry....! the row is empty!");
				System.out.println("_________________________________________________________");

				statement2.close();
				break;
			case 2: 
				System.out.println("Enter issued ID");
				int i=sc.nextInt();

				String sql4="SELECT DATEDIFF(CURDATE(),Return_DATE) FROM ISSUED where (issued_id ='"+i+"' )";
				Statement stmt4 = con.createStatement();
				//String da=sc.next();
				ResultSet rs10 = stmt4.executeQuery(sql4);
				int fine=0;
				while(rs10.next())
				{
					try {
						String period = rs10.getString("DATEDIFF(CURDATE(),Return_DATE)");
						System.out.println(period);
						int d=Integer.parseInt(period);
						if(d<=3) {
							System.out.println("No fine amount....!");
							String sq3 = "SELECT price FROM BOOKS WHERE b_id='"+i+"'";
							Statement state3 = con.createStatement();
							ResultSet rs = state3.executeQuery(sq3);
							while(rs.next())
							{
								int amount = rs.getInt("price");
								System.out.println("Price of the book "+amount);
								System.out.println("Price to be paid "+ ((int)fine+amount));
							}

						}
						else if(d<3 && d>=10)
						{
							fine=fine+10;
							System.out.println("the fine amount is"+fine+" Rs");
							String sq3 = "SELECT price FROM BOOKS WHERE b_id='"+i+"'";
							Statement state3 = con.createStatement();
							ResultSet rs = state3.executeQuery(sq3);
							while(rs.next())
							{
								int amount = rs.getInt("price");
								System.out.println("Price of the book "+amount);
								System.out.println("Price to be paid "+ ((int)fine+amount));
							}
						}
						else if(d>10 && d<=100)
						{
							fine=fine+20;
							System.out.println("the fine amount is "+fine+" Rs");
							String sq3 = "SELECT price FROM BOOKS WHERE b_id='"+i+"'";
							Statement state3 = con.createStatement();
							ResultSet rs = state3.executeQuery(sq3);
							while(rs.next())
							{
								int amount = rs.getInt("price");
								System.out.println("Price of the book "+amount);
								System.out.println("Price to be paid "+ ((int)fine+amount));
							}
						}
						break;
					}


					catch (SQLException e1) {
						System.out.println(e1);
					}
				}
				System.out.println("_________________________________________________________");

				break;
			case 3:
				String sql3 = "SELECT * FROM issued";
				PreparedStatement statement3 = con.prepareStatement(sql3);
				//System.out.println("Enter User Id");
				//statement3.setInt(1,sc.nextInt());
				ResultSet result = statement3.executeQuery();

				int count=0;

				while(result.next())
				{
					int b_id = result.getInt("b_id");
					String issue_date = result.getString("issued_date");
					String r_date = result.getString("return_date");
					int user_id = result.getInt("u_id");
					String book_name=result.getString("b_name");
					String user_name=result.getString("u_name");
					int issued_id = result.getInt("issued_id");

					count++;
					System.out.println("Book Isuue ID: " +issued_id + "\nBook Issue Date: " + issue_date + "\nBook Returned Date: " + r_date + "\nBook Name: " + book_name + "\nUser Name: " + user_name + "\nUser ID: " + user_id + "\nBook ID: " +b_id);
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				}
				System.out.println("Total Issued: "+count);
				System.out.println("_________________________________________________________");
				break;
			case 4:
				System.out.println("U have chosen to exit...");
				System.out.println("exited from the page  :)  ");
				break;
			default:
				System.out.println("Enter choice from 1 - 3....!");


			}
		}while(m1!=4);
	}
}


