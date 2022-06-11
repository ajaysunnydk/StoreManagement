package com.StoreManagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Cart {

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
	
	public void addToCart() {
		// TODO Auto-generated method stub
		Connection con;
		try {
			con = getMySQLConnection();
			String q1 = "insert into items(itemId,itemName,itemQty) values(?,?,?) ";
			PreparedStatement stmt;
			Scanner sc = new Scanner(System.in);
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
				//System.out.println("Items Inserted.......");
				System.out.println("Add More items? (y/n): ");
				String more = sc.next();
				if(more.equals("y"))
				{
					addToCart();
				}
				else
				{
					StoreFunctions storeFunctions = new StoreFunctions();
					storeFunctions.userDashboard();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sc.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void viewCart() {
		// TODO Auto-generated method stub
		
	}

	public void deleteFromCart() {
		// TODO Auto-generated method stub
		
	}

	public void updateCart() {
		// TODO Auto-generated method stub
		
	}

}
