package com.jspiders.JDBCCarDekho1.mainMenu;

import java.util.Scanner;

import com.jspiders.JDBCCarDekho1.operation.CarOperations;

public class CarDekhoMenu {
static boolean choose = true;
static CarOperations carOperations;
	
	public static void mainMenu() {
		carOperations = new CarOperations();
   	 Scanner scanner = new Scanner(System.in);
   	 while(choose==true) {
   		System.out.println("=====MainManu====\n"
				+"1.Add Car\n"
				+"2.View All Cars \n"
				+"3.Search Car\n"
				+"4.Remove Car\n"
				+"5.Update Car\n"
				+"6.Exit");
		System.out.println("SELECT ONE OPTION: ");
     	int  choise = scanner.nextInt();
     	 switch (choise) {
 		case 1:
 			carOperations.addCar(scanner);
 			break;
 		case 2:
 			carOperations.viewAllCar(scanner);
 			break;
 		case 3:
 			carOperations.searchMethod(scanner);
 			break;
 		case 4:
 			carOperations.removeCar(scanner);
 			break;
 		case 5:
 			carOperations.updateCar(scanner);
 			break;
 		case 6:
 			choose=false;
 			break;
 		default:
 			System.out.println("Invalid input plz try Again");
 			break;

 		}
      }	
	}
}
