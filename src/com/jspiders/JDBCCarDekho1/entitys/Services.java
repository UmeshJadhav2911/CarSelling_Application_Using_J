package com.jspiders.JDBCCarDekho1.entitys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jspiders.JDBCCarDekho1.mainMenu.CarDekhoMenu;

public class Services {	
		private static Connection connection;
		private static PreparedStatement preparedStatement;
		private static String query;
		private static ResultSet resultSet;
		
		public static void signUp(Scanner scanner) {
			
			System.out.println("Enter user id");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter user name");
			String name = scanner.nextLine();
			System.out.println("Enter user Email Address");
			String email = scanner.nextLine();
			System.out.println("Enter the password");
			String password = scanner.nextLine();		
			
			try {
				openConnection();
				query = "insert into App_Admin values(?,?,?,?)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, email );
				preparedStatement.setString(4, password);
				int res = preparedStatement.executeUpdate();
				if (res==1) {
					System.out.println("++++++Signed up+++++++");
					System.out.println(" ");
				}else {
					System.out.println("--------Error--------\n"+" ");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		public static void logIn(Scanner scanner) {
			scanner.nextLine();
			System.out.println("Enter user Email Address");
			String email = scanner.nextLine();
			System.out.println("Enter the password");
			String password = scanner.nextLine();		
			
			try {
				openConnection();
				query = "select * from App_Admin where email = ? AND password =?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					System.out.println("+++++++Log in++++++");
					System.out.println("*******WELCOME TO CARDEKHO*******");
					System.out.println(" ");
					CarDekhoMenu.mainMenu();
				}else {
					System.out.println("--------INVALID CREDENTIALS--------\n"+" ");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
		public static void openConnection() throws SQLException {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weja4","root","root");
		}
		public static void closeConnection() throws SQLException {
			if (connection != null) {
				connection.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}
	
}
