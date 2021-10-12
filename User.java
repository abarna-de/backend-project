package projo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class User extends DBconnect{
	public static void useraccess() throws Exception 
	{
		int m1;
		Scanner sc = new Scanner(System.in); 
		do {
			System.out.println("Enter 1 to borrow book");

			System.out.println("Enter 2 to view issued books");
			System.out.println("Enter 3 to know price, amount and fine");
			System.out.println("Enter 4 to view entire books in field");
			System.out.println("Enter 5 to exit");
			System.out.println("Enter ur choice");
			m1=sc.nextInt();
			switch(m1)
			{
				case 1:
					String sql1 = "INSERT INTO ISSUED (u_name, u_id, b_id, b_name,issued_date,return_date) VALUES (?, ?, ?, ?,?,?)";
					PreparedStatement statement1 = con.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
					System.out.println("User name");
					sc.nextLine();
					String uname=sc.nextLine();
					statement1.setString(1, uname);
					System.out.println("User id");
					statement1.setInt(2, sc.nextInt());
					System.out.println("Book id");
					statement1.setInt(3, sc.nextInt());
					System.out.println("Book name");
					sc.nextLine();
					String bname=sc.nextLine();
					statement1.setString(4, bname);
					System.out.println("Book issue Date");
					LocalDate localDateTime = LocalDate.now();
				    System.out.println(localDateTime);

				      java.sql.Date date = java.sql.Date.valueOf(localDateTime);

					statement1.setDate(5, date);


					System.out.println("Enter Book return Date");
					 LocalDate returnDate =  LocalDate.now();
					 java.sql.Date rdate = java.sql.Date.valueOf(returnDate.plusDays(15));
					 System.out.println(rdate);
					 statement1.setDate(6, rdate);
					 ResultSet res = statement1.getGeneratedKeys();
						while (res.next()) 
						{
							System.out.println("Issued ID: "+res.getInt(1));
						}



					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

					int rows = statement1.executeUpdate();
					if(rows > 0)
					{
						System.out.println("1 row is inserted...!");
					}
					System.out.println("_________________________________________________________");

					statement1.close();
					break;

				case 2:
					String sql3 = "SELECT * FROM issued where (u_id= ?)";
					PreparedStatement statement3 = con.prepareStatement(sql3);
					System.out.println("Enter User Id");
					statement3.setInt(1,sc.nextInt());
					ResultSet result = statement3.executeQuery();

					//int count=0;

					while(result.next())
					{
						int b_id = result.getInt("b_id");
						String issue_date = result.getString("issued_date");
						String r_date = result.getString("return_date");
						int user_id = result.getInt("u_id");
						String book_name=result.getString("b_name");
						String user_name=result.getString("u_name");
						int issued_id = result.getInt("issued_id");

						//count++;
						System.out.println("Book Isuue ID: " +issued_id + "\nBook Issue Date: " + issue_date + "\nBook Returned Date: " + r_date + "\nBook Name: " + book_name + "\nUser Name: " + user_name + "\nUser ID: " + user_id + "\nBook ID: " +b_id);
						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

					}
					System.out.println("_________________________________________________________");

					break;

				case 3:
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
				case 4:
					 System.out.println("\nEnter the field");
					 String a1 =sc.next();

					 String sql5 = "SELECT * FROM BOOKS WHERE b_field='"+a1.toLowerCase()+"'";
					 Statement statement5 = con.createStatement();

		             ResultSet re = statement5.executeQuery(sql5);
		             if(re.next() == false)
					 {
		            	 System.out.println("Sorry...There is no books available in the field");
					 }
		             else
		             {
			             try {
			            	 do
			            	 {
			            	 	int a_id = re.getInt("b_id");
								String a_name = re.getString("b_name");
								String a_publisher = re.getString("b_publisher");
								String a_pub_yr = re.getString("b_pub_yr");
								String a_field = re.getString("b_field");
								String a_author_name = re.getString("b_author_name");
								System.out.println("Book ID: " +a_id + "\nBook Name: " + a_name + "\nBook Publisher: " + a_publisher + "\nBook Published Year:  " + a_pub_yr + "\nField: " + a_field + "\nBook Author Name" + a_author_name);
								System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			            	 }while(re.next());
			             }catch(SQLException e1)
			             {
			            	 System.out.println("sorry...!it does not exist...!try again:)");
			             }
		             }
					 System.out.println("_________________________________________________________");

		             break;	

				case 5:
					System.out.println("U have chosen to exit...");
					System.out.println("exited from the page  :)  ");
					Admin.admin();
					break;
				default:
					System.out.println("Enter choice from 1 - 5....!");
			}
		}while(m1!=5);


	}
	}


