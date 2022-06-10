package com.StoreManagement;
import java.io.*;
import java.util.Scanner;
public class MainPage {
	public static void main(String[] args) throws IOException 
	{

		
			Scanner sc= new Scanner(System.in);
			String loop="y";
			while(loop.equals("y")) 
			{
				
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("\n Enter your choice: ");
				System.out.println(" ");
			
				StoreFunctions sf = new StoreFunctions();
				
				int a = sc.nextInt();
				switch(a) {
				case 1:
					sf.registerUser();
					break;
				case 2:
					sf.login();
					break;
			
				default:
					System.out.println("Enter valid choice: ");
				}
				System.out.println("Do you wish to continue: y/n");
				loop = sc.next();
			}
			
			sc.close();
		
		
	}
}
