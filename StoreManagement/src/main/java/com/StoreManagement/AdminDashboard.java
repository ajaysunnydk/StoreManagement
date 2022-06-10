package com.StoreManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminDashboard {
	StoreFunctions sf = new StoreFunctions();
	Scanner sc = new Scanner(System.in);
	
	public void addItems() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = sf.getMySQLConnection();
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
				sf.adminDashboard();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void viewItems() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = sf.getMySQLConnection();
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
		Connection con = sf.getMySQLConnection();
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
				sf.adminDashboard();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateItems() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = sf.getMySQLConnection();
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
				sf.adminDashboard();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewUser() throws IOException
	{
		// TODO Auto-generated method stub
		Connection con = sf.getMySQLConnection();
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

	
	
}
