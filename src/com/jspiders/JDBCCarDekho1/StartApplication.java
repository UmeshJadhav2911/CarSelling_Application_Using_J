package com.jspiders.JDBCCarDekho1;
import java.util.Scanner;

import com.jspiders.JDBCCarDekho1.entitys.Services;


public class StartApplication {
		
		public static void main(String[] args) {
			boolean flag = true;
			Scanner scanner = new Scanner(System.in);
			while (flag) {
				System.out.println("Enter 1 for sign Up \nEnter 2 for login \nEnter 3 for Exit");
				int choise = scanner.nextInt();
				switch (choise) {
				case 1:
					Services.signUp(scanner);
					break;
				case 2:
					Services.logIn(scanner);
					break;
				case 3:
					System.out.println("....Thank You....");
		 			System.out.println("....Visit Again....");
					flag = false;
					break;
				default:
					System.out.println("Invalid choise");
				}
			}
			scanner.close();
			
		}
		
	
}
