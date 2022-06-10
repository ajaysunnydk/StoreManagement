package com.StoreManagement;
import java.sql.*;
import java.io.*;
import java.util.Scanner;
import java.util.Properties;
public class StoreFunctions {
	
Scanner sc = new Scanner(System.in);


	public Connection getMySQLConnection() throws IOException
	{
		FileInputStream fis=new FileInputStream("jdbc.prop");
		Properties p=new Properties ();
		p.load (fis);
		String conURL= (String) p.get ("URL");
        String conUsername= (String) p.get ("Uname");
        String conPassword= (String) p.get ("password");

		Connection con = null;
		try
		{
			con = DriverManager.getConnection(conURL,conUsername,conPassword);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return con;
	}

	public void registerUser() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = getMySQLConnection();
		System.out.println(".....User Registration Page.....");
		
		System.out.println("Enter Username: ");
		String uname = sc.next();
		
		System.out.println("Enter Password: ");
		String pass = sc.next();
		
		String q0 = "select * from regdetails where uname=?";
		String q1 = "insert into regDetails(uname,password) values(?,?)";
		
		try 
		{
			PreparedStatement stmt = con.prepareStatement(q0);
			stmt.setString(1, uname);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				System.out.println("Username "+uname+" already exists");
			}
			else 
			{
				stmt = con.prepareStatement(q1);
				stmt.setString(1, uname);
				stmt.setString(2, pass);
				
				stmt.executeUpdate();
				System.out.println("_____Registration Successfull_____");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public String login() throws IOException
	{
		// TODO Auto-generated method stub
		String existingRole = null;
		
		Connection con = getMySQLConnection();
		System.out.println(".....Welcome to Login Page.....");
		
		System.out.println("Enter Username: ");
		String uname = sc.next();
		
		System.out.println("Enter Password: ");
		String pass = sc.next();
		
		String q1 = "select * from regdetails where uname=? AND password=?";
		try 
		{
			PreparedStatement stmt = con.prepareStatement(q1);
			stmt.setString(1, uname);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				
				existingRole  = rs.getString(3);
				
				System.out.println("************************************");
				System.out.println("***********Login Success************");
				
				if(existingRole.equals("admin"))
				{
					System.out.println("_________________Admin__________________");
					System.out.println("1.Continue to Dashboard");
					System.out.println("2.Continue to Register");
					System.out.println("\n Enter your choice: ");
					int choice = sc.nextInt();
					if(choice == 1){
						adminDashboard();
					}
					else if(choice == 2) {
						
						System.out.println("Enter new Username: ");
						uname = sc.next();
						
						System.out.println("Enter your Password: ");
						pass = sc.next();
						
						System.out.println("Enter Role (user/admin): ");
						String role = sc.next();
						
						String q0 = "select * from regdetails where uname=?";
						String q2 = "insert into regDetails(uname,password,role) values(?,?,?)";
						
						try 
						{
							stmt = con.prepareStatement(q0);
							stmt.setString(1, uname);
							rs = stmt.executeQuery();
							if(rs.next())
							{
								System.out.println("Username "+uname+" already exists");
							}
							else
							{
								stmt = con.prepareStatement(q2);
								stmt.setString(1, uname);
								stmt.setString(2, pass);
								stmt.setString(3, role);
								
								stmt.executeUpdate();
								System.out.println("_____Registration Successfull_____");
							}
						} 
						catch (SQLException e) 
						{
							e.printStackTrace();
						}
						
					}
					else
					{
						System.out.println("Enter valid choice.");
					}
				}
				else
				{
					System.out.println("__________________User__________________");
					userDashboard();
				}
				
				
	
			}
			else
			{
				System.out.println("************************************");
				System.out.println("***********Login Failed*************");
				System.out.println("***Invalid Username and Password****");
			}
			
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return existingRole;
	
	}

	private void userDashboard() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("1. Add items to cart");
		System.out.println("2. View items in cart");
		System.out.println("3. Remove items from cart");
		System.out.println("4. Update items in cart");
		System.out.println("\n Enter your choice: ");
	
		Cart ct = new Cart();
		
		int a = sc.nextInt();
		String loop="y";
		while(loop.equals("y"))
		{
			switch(a) 
			{
				case 1:
					ct.addToCart();
					break;
				case 2:
					ct.viewCart();
					System.out.println("\n \n .....Viewing Items.....\n \n");
					viewItems();
					break;
				case 3:
					ct.deleteFromCart();
					break;
				case 4:
					ct.updateCart();
					break;
				default:
					System.out.println("Enter valid choice: ");
			}
			System.out.println("Logout : y/n : ");
			loop = sc.next();
		}		
	}

	private void adminDashboard() throws IOException {
		// TODO Auto-generated method stub

		System.out.println("1. Add items to store");
		System.out.println("2. View items in store");
		System.out.println("3. Remove items from store");
		System.out.println("4. Update item Quantities");
		System.out.println("5. View All Users and Admins");
		System.out.println("\n Enter your choice: ");
	
		
		int a = sc.nextInt();
		String loop="y";
		while(loop.equals("y"))
		{
			switch(a) 
			{
				case 1:
					addItems();
					break;
				case 2:
					viewItems();
					break;
				case 3:
					deleteItems();
					break;
				case 4:
					updateItems();
					break;
				case 5:
					viewUser();
					break;
				default:
					System.out.println("Enter valid choice: ");
			}
			System.out.println("Logout : y/n : ");
			loop = sc.next();
		}		
	}

	private void viewUser() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = getMySQLConnection();
		String q1 = "select * from regdetails; ";
		PreparedStatement stmt;
		try 
		{
			stmt = con.prepareStatement(q1);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				System.out.println("Username    |    Role");
				System.out.println(rs.getString(1)+" "+rs.getString(3));
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	public void addItems() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = getMySQLConnection();
		String q1 = "insert into items(itemId,itemName,itemQty) values(?,?,?) ";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(q1);
			
			System.out.println("Enter ID: ");
			int id = sc.nextInt();
			
			System.out.println("Enter Name: ");
			String name = sc.next();
			
			System.out.println("Enter Quantity: ");
			int qty = sc.nextInt();
			
			
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setInt(3, qty);
			
			stmt.executeUpdate();
			
			System.out.println("Items Inserted.......");
			System.out.println("Add More items? (y/n): ");
			String more = sc.next();
			if(more.equals("y"))
			{
				addItems();
			}
			else
			{
				adminDashboard();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void viewItems() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = getMySQLConnection();
		String q1 = "select * from items; ";
		PreparedStatement stmt;
		try 
		{
			stmt = con.prepareStatement(q1);
			ResultSet rs = stmt.executeQuery();
			System.out.println("item ID |  item Name    |    Qty");
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteItems() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = getMySQLConnection();
		String q1 = "delete from items where itemId=? ";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(q1);
			
			System.out.println("Enter ID: ");
			int id = sc.nextInt();
			
			
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
			System.out.println("Items Deleted.......");
			System.out.println("Delete More items? (y/n): ");
			String more = sc.next();
			if(more.equals("y"))
			{
				deleteItems();
			}
			else
			{
				adminDashboard();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateItems() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = getMySQLConnection();
		String q1 = "update items set itemId = ?,itemName = ?,itemQty=? where itemId=? ";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(q1);
			
			System.out.println("Enter ID: ");
			int id = sc.nextInt();
			
			System.out.println("Enter new ID: ");
			int newId = sc.nextInt();
			
			System.out.println("Enter new item Name:");
			String newName = sc.next();
			
			System.out.println("Enter new item Name:");
			int newQty = sc.nextInt();
			
			stmt.setInt(1, newId);
			stmt.setString(2, newName);
			stmt.setInt(3, newQty);
			stmt.setInt(4, id);
			stmt.executeUpdate();
			
			System.out.println("Items Updated.......");
			System.out.println("Update More items? (y/n): ");
			String more = sc.next();
			if(more.equals("y"))
			{
				updateItems();
			}
			else
			{
				adminDashboard();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
