package projo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Book extends DBconnect{
	public static void book() throws Exception {
		int n;
		Scanner sc = new Scanner(System.in); 
		do {
			System.out.println("Enter 1 to add book");
			System.out.println("Enter 2 to delete book");
			System.out.println("Enter 3 to search a book");
			System.out.println("Enter 4 to view entire book list");
			System.out.println("Enter 5 to view entire books in field");
			System.out.println("Enter 6 to exit");
			System.out.println("Enter ur choice");
			n=sc.nextInt();
			switch(n)
			{
				case 1:
					String sql1 = "INSERT INTO books (b_id, b_name, b_publisher, b_pub_yr, b_field, b_author_name,price) VALUES (?, ?, ?, ?, ?, ?,?)";
					PreparedStatement statement1 = con.prepareStatement(sql1);

					System.out.println("Enter the Book id");
					statement1.setInt(1, sc.nextInt());
					System.out.println("Enter the Book name");
					sc.nextLine();
					String bname = sc.nextLine();
					statement1.setString(2, bname);
					System.out.println("Enter the Book publisher");
					String bpub = sc.nextLine();
					statement1.setString(3, bpub);
					System.out.println("Enter the Book published year");
					String bpubyr = sc.nextLine();
					statement1.setString(4, bpubyr);
					System.out.println("Enter the Book field/domain");
					String bfield = sc.next();
					statement1.setString(5, bfield);
					System.out.println("Enter the Book Author Name");
					sc.nextLine();
					String baut = sc.nextLine();
					statement1.setString(6, baut);
					System.out.println("Enter the Book Price");
					int price = sc.nextInt();
					statement1.setLong(7, price);
					System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

					int rows = statement1.executeUpdate();
					if(rows > 0)
					{
						System.out.println("1 row is inserted...!");
					}
					System.out.println("_______________________________________________");

					statement1.close();
					break;
				case 2: 
					String sql2 = "DELETE FROM books WHERE (b_id = ?)";
					PreparedStatement statement2 = con.prepareStatement(sql2);
					System.out.println("Enter the Book id");
					statement2.setString(1, sc.next());
	                //statement2.setString(2, sc.next());
					System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

					int row = statement2.executeUpdate();
					if(row > 0)
					{
						System.out.println("1 row is deleted...!");
					}
					else
						System.out.println("Sorry....! the row is empty!");
					System.out.println("_______________________________________________");

					statement2.close();
					break;
				case 3:
					System.out.println("Enter the book name to be searched");
					 sc.nextLine();
					 String a =sc.nextLine();
					 String sql3 = "SELECT * FROM BOOKS WHERE b_name='"+a.toLowerCase()+"'";
					 Statement statement3 = con.createStatement();

		             ResultSet rs = statement3.executeQuery(sql3);
		             try {
		            	 while(rs.next())
		            	 {
		            	 	int b_id = rs.getInt("b_id");
							String b_name = rs.getString("b_name");
							String b_publisher = rs.getString("b_publisher");
							String b_pub_yr = rs.getString("b_pub_yr");
							String b_field = rs.getString("b_field");
							String b_author_name = rs.getString("b_author_name");
							System.out.println("Book ID: " +b_id + "\nBook Name: " + b_name + "\nBook Publisher: " + b_publisher + "\nBook Published Year:  " + b_pub_yr + "\nField: " + b_field + "\nBook Author Name:" + b_author_name);
							System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            	 }

		             }catch(SQLException e1)
		             {
		            	 System.out.println("sorry...!it does not exist...!try again:)");
		             }
					 System.out.println("_________________________________________________________");

		             break;

				case 4:
					String sql4 = "SELECT * FROM books";
					Statement statement4 = con.createStatement();
					ResultSet result = statement4.executeQuery(sql4);

					//int count=0;

					while(result.next())
					{
						int r_id = result.getInt("b_id");
						String r_name = result.getString("b_name");
						String r_publisher = result.getString("b_publisher");
						String r_pub_yr = result.getString("b_pub_yr");
						String r_field = result.getString("b_field");
						String r_author_name = result.getString("b_author_name");
						//count++;
						System.out.println("Book ID: " +r_id +  "\nBook Name: " + r_name + "\nBook Publisher: " + r_publisher + "\nBook Published Year:  " + r_pub_yr + "\nField: " + r_field + "\nBook Author Name: " + r_author_name);
						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

					}
					 System.out.println("_________________________________________________________");

					break;
				case 5:

					 System.out.println("Available fields : ");
					 System.out.println("----->COMICS");
					 System.out.println("----->MAGAZINE");
					 System.out.println("----->MECH");
					 System.out.println("----->CSE");
					 System.out.println("----->ECE");
					 System.out.println("----->IT");
					 System.out.println("----->AGRI");
					 System.out.println("----->GOVERNMENT EXAMS");
					 System.out.println("\n___________________________________________________________");
					 System.out.println("\nEnter the field");
					 String a1 =sc.next();

					 String sql5 = "SELECT * FROM BOOKS WHERE b_field='"+a1.toLowerCase()+"'";
					 Statement statement5 = con.createStatement();

		             ResultSet res = statement5.executeQuery(sql5);
		             if(res.next() == false)
					 {
		            	 System.out.println("Sorry...There is no books available in the field");
					 }
		             else
		             {
			             try {
			            	 do
			            	 {
			            	 	int a_id = res.getInt("b_id");
								String a_name = res.getString("b_name");
								String a_publisher = res.getString("b_publisher");
								String a_pub_yr = res.getString("b_pub_yr");
								String a_field = res.getString("b_field");
								String a_author_name = res.getString("b_author_name");
								System.out.println("Book ID: " +a_id + "\nBook Name: " + a_name + "\nBook Publisher: " + a_publisher + "\nBook Published Year:  " + a_pub_yr + "\nField: " + a_field + "\nBook Author Name" + a_author_name);
								System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			            	 }while(res.next());
			             }catch(SQLException e1)
			             {
			            	 System.out.println("sorry...!it does not exist...!try again:)");
			             }
		             }
					 System.out.println("_________________________________________________________");

		             break;	
				case 6:
					System.out.println("U have chosen to exit...");
					System.out.println("exited from the page  :)  ");
					System.out.println("______________________________________________________________");
					break;
				default:
					System.out.println("Enter choice from 1 - 6....!");


			}
		}while(n!=6);

		}
		}


