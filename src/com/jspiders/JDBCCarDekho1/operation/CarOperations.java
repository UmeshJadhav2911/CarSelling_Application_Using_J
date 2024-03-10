package com.jspiders.JDBCCarDekho1.operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CarOperations {
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static String quary;
	private static PreparedStatement preparedStatement1;
	private static ResultSet resultSet1 ;
	private static String quary1;


//----------------------------------Add Car-----------------------------------------------------------------	  
	public void addCar(Scanner scanner) {
		
		try {
			openConnection();
			quary = "insert into car values(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(quary);
			System.out.println("HOW MANY CAR'S YOU WANT TO INSERT?");
			int record = scanner.nextInt();
			
			int count = 0;
			for (int i = 1; i <= record; i++) {
				System.out.println("Enter car id");
				int id = scanner.nextInt();
				checkID(id);
				if (!resultSet1.next()) {
					scanner.nextLine();
					System.out.println("Enter car Name");
					String name = scanner.nextLine();
					System.out.println("Enter Model Name");
					String model = scanner.nextLine();
					System.out.println("Enter Brand Name");
					String brand = scanner.nextLine();
					System.out.println("Enter car Price");
					int price = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter Car FuelTYpe");
					String fuelType = scanner.nextLine();
					System.out.println("Enter Car Colour");
					String colour = scanner.nextLine();
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, name);
					preparedStatement.setString(3, model);
					preparedStatement.setString(4, brand);
					preparedStatement.setInt(5, price);
					preparedStatement.setString(6, fuelType);
					preparedStatement.setString(7, colour);
					preparedStatement.addBatch();
					count++;
					if (i < record) {
						System.out.println("------Enter " + (i + 1) + "Car's Record-------");
					}
				} else {
					System.out.println("===This Car Id is Present== " + "\n===Use New ID===");
					System.out.println(" ");
				}
			}
			if (count > 0) {
				preparedStatement.executeBatch();
				System.out.println("****** " + count + " CAR'S ADD SUCESSFULLY ******");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	//------------------------Check id is present or not--------------------------------------------
	public static ResultSet checkID(int id) {
				
		try {
			openConnection();
			quary1 = "select * from car where id=?";
			preparedStatement1 = connection.prepareStatement(quary1);
			preparedStatement1.setInt(1, id);
			resultSet1 = preparedStatement1.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
				return resultSet1;
		
	}

//----------------------------View All Car--------------------------------------------------------	  
	public void viewAllCar(Scanner scanner) {
		try {
			openConnection();
			statement = connection.createStatement();
			quary = "select * from car";
			resultSet = statement.executeQuery(quary);
			boolean key=true;
			while (resultSet.next()) {
				key=false;
				System.out.print(resultSet.getInt(1) + " ");
				System.out.print(resultSet.getString(2) + " ");
				System.out.print(resultSet.getString(3) + " ");
				System.out.print(resultSet.getString(4) + " ");
				System.out.print(resultSet.getInt(5) + " ");
				System.out.print(resultSet.getString(6) + " ");
				System.out.println(resultSet.getString(7) + " ");
			}
			if (key) {
				System.out.println("====SORRY, CURRENTLY CAR NOT PRESENT====");
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

//--------------------------------- Search Car --------------------------------------------------------------------	  
	public void searchMethod(Scanner scanner) {
		boolean take = true;
		while (take == true) {
			System.out.println("1.Search By Id\n" + "2.Search by Name\n" + "3.Search By Brand\n"
					+ "4.Search By FuelType\n" + "5.Search By Price\n" + "6.Search By Colour\n" + "7.Go Back");
			System.out.println("SELECT ONE OPTION: ");
			int search = scanner.nextInt();
			switch (search) {
			case 1:
				SearchById(scanner);
				break;
			case 2:
				searchByName(scanner);
				break;
			case 3:
				searchByBrand(scanner);
				break;
			case 4:
				searchByFuelType(scanner);
				break;
			case 5:
				searchByPrice(scanner);
				break;
			case 6:
				searchByColour(scanner);
				break;
			case 7:
				return;
			default:
				System.out.println("Invalid Input  Plz Try Again ");
				break;
			}
		}

	}

	// -------------------search By ID-----------------
	private void SearchById(Scanner scanner) {
		try {
			openConnection();
			System.out.println("Enter car id");
			int id = scanner.nextInt();
			String quary = "select * from car where id=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.print(resultSet.getInt(1) + " ");
				System.out.print(resultSet.getString(2) + " ");
				System.out.print(resultSet.getString(3) + " ");
				System.out.print(resultSet.getString(4) + " ");
				System.out.print(resultSet.getInt(5) + " ");
				System.out.print(resultSet.getString(6) + " ");
				System.out.println(resultSet.getString(7) + " ");

			} else {
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(" ");
	}

	// -------------------Search By Name----------------
	private void searchByName(Scanner scanner) {
		try {
			openConnection();
			System.out.println("Enter car Name");
			String name = scanner.next();
			String quary = "select * from car where Name=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			boolean key=true;
			while (resultSet.next()) {
				key=false;
				System.out.print(resultSet.getInt(1) + " ");
				System.out.print(resultSet.getString(2) + " ");
				System.out.print(resultSet.getString(3) + " ");
				System.out.print(resultSet.getString(4) + " ");
				System.out.print(resultSet.getInt(5) + " ");
				System.out.print(resultSet.getString(6) + " ");
				System.out.println(resultSet.getString(7) + " ");
			}
			if (key) {
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(" ");

	}

	// -------------------Search By Brand--------------
	private void searchByBrand(Scanner scanner) {
		try {
			openConnection();
			System.out.println("Enter car Brand");
			String brand = scanner.next();
			String quary = "select * from car where Brand=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setString(1, brand);
			resultSet = preparedStatement.executeQuery();
			boolean key =true;
				while (resultSet.next()) {
					key=false;
					System.out.print(resultSet.getInt(1) + " ");
					System.out.print(resultSet.getString(2) + " ");
					System.out.print(resultSet.getString(3) + " ");
					System.out.print(resultSet.getString(4) + " ");
					System.out.print(resultSet.getInt(5) + " ");
					System.out.print(resultSet.getString(6) + " ");
					System.out.println(resultSet.getString(7) + " ");
				}
			if(key){
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(" ");

	}

	// -------------------Search by Fuel TYpe-----------
	private void searchByFuelType(Scanner scanner) {
		try {
			openConnection();
			System.out.println("Enter car Fuel Type");
			String fuel = scanner.next();
			String quary = "select * from car where FuelType=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setString(1, fuel);
			resultSet = preparedStatement.executeQuery();
			boolean key=true;
				while (resultSet.next()) {
					key=false;
					System.out.print(resultSet.getInt(1) + " ");
					System.out.print(resultSet.getString(2) + " ");
					System.out.print(resultSet.getString(3) + " ");
					System.out.print(resultSet.getString(4) + " ");
					System.out.print(resultSet.getInt(5) + " ");
					System.out.print(resultSet.getString(6) + " ");
					System.out.println(resultSet.getString(7) + " ");
				}
			 if(key){
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(" ");

	}

	// -------------------Search by Price-----------------
	private void searchByPrice(Scanner scanner) {
		try {
			openConnection();
			System.out.println("Enter car Min-Price");
			int minPrice = scanner.nextInt();
			System.out.println("Enter Car Max-Price");
			int maxPrice = scanner.nextInt();
			String quary = "select * from car where Price between ? and ?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setInt(1, minPrice);
			preparedStatement.setInt(2, maxPrice);
			resultSet = preparedStatement.executeQuery();
			boolean key = true;
				while (resultSet.next()) {
					System.out.print(resultSet.getInt(1) + " ");
					System.out.print(resultSet.getString(2) + " ");
					System.out.print(resultSet.getString(3) + " ");
					System.out.print(resultSet.getString(4) + " ");
					System.out.print(resultSet.getInt(5) + " ");
					System.out.print(resultSet.getString(6) + " ");
					System.out.println(resultSet.getString(7) + " ");
				}
				if(key){
					System.out.println("===CAR NOT FOUND===");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(" ");

	}

	// ------------------Search by Color---------------
	private void searchByColour(Scanner scanner) {
		try {
			openConnection();
			System.out.println("Enter car Color");
			String colour = scanner.next();
			String quary = "select * from car where Colour=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setString(1, colour);
			resultSet = preparedStatement.executeQuery();
			boolean key =true;
				while (resultSet.next()) {
					System.out.print(resultSet.getInt(1) + " ");
					System.out.print(resultSet.getString(2) + " ");
					System.out.print(resultSet.getString(3) + " ");
					System.out.print(resultSet.getString(4) + " ");
					System.out.print(resultSet.getInt(5) + " ");
					System.out.print(resultSet.getString(6) + " ");
					System.out.println(resultSet.getString(7) + " ");
				}
				if(key){
					System.out.println("===CAR NOT FOUND===");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(" ");

	}

//----------------------------Remove Car-----------------------------------------------------	
	public void removeCar(Scanner scanner) {
		boolean take = true;
		while (take == true) {
			System.out.println("1.Remove By Id\n" + "2.Remove By Name\n" + "3.Remove By Brand\n"
					+ "4.Remove By FuelType\n" + "5.Remove By Price\n" + "6.Remove By Colour\n" + "7.Go Back");
			System.out.println("SELECT ONE OPTION: ");
			int remove = scanner.nextInt();
			switch (remove) {
			case 1:
				removeById(scanner);
				break;
			case 2:
				removeByName(scanner);
				break;
			case 3:
				removeByBrand(scanner);
				break;
			case 4:
				removeByFuelType(scanner);
				break;
			case 5:
				removeByPrice(scanner);
				break;
			case 6:
				removeByColour(scanner);
				break;
			case 7:
				return;
			default:
				System.out.println("Invalid Input  Plz Try Again ");
				break;
			}
		}
	}

	// --------------Remove By Id-------------------
	private void removeById(Scanner scanner) {
		System.out.println("Enter Car ID TO Delete");
		int id = scanner.nextInt();
		try {
			openConnection();
			quary = " delete from  car where id=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setInt(1, id);
			int row = preparedStatement.executeUpdate();
			if (row != 0) {
				System.out.println(row + " deleted");
			} else {
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" ");
	}

	// ---------------Remove By Name---------------------
	private void removeByName(Scanner scanner) {
		System.out.println("Enter Car Name TO Delete");
		String name = scanner.next();
		try {
			openConnection();
			quary = " delete from  car where Name=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setString(1, name);
			int row = preparedStatement.executeUpdate();
			if (row != 0) {
				System.out.println(row + " deleted");
			} else {
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" ");
	}

	// ---------------Remove by Brand-------------------
	private void removeByBrand(Scanner scanner) {
		System.out.println("Eter Car Brand TO Delete");
		String brand = scanner.next();
		try {
			openConnection();
			quary = " delete from  car where Brand=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setString(1, brand);
			int row = preparedStatement.executeUpdate();
			if (row != 0) {
				System.out.println(row + " deleted");
			} else {
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" ");
	}
    
	//-----------------------Remove By FuelType----------------------------------------------------
	private void removeByFuelType(Scanner scanner) {
		System.out.println("Enter Car Fuel Type TO Delete");
		String fuel = scanner.next();
		try {
			openConnection();
			quary = " delete from  car where FuelType=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setString(1, fuel);
			int row = preparedStatement.executeUpdate();
			if (row != 0) {
				System.out.println(row + " deleted");
			} else {
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" ");
	}
    
	//-----------------------Remove By Price---------------------------------------------------------
	private void removeByPrice(Scanner scanner) {
		try {
			openConnection();
			System.out.println("Enter car Min-Price to remove");
			int minPrice = scanner.nextInt();
			System.out.println("Enter Car Max-Price to remove");
			int maxPrice = scanner.nextInt();
			String quary = "delete from car where Price between ? and ?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setInt(1, minPrice);
			preparedStatement.setInt(2, maxPrice);
			int row = preparedStatement.executeUpdate();
			if (row != 0) {
				System.out.println(row + " deleted");
			} else {
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(" ");
	}

	//------------------Remove By Color--------------------------
	private void removeByColour(Scanner scanner) {
		System.out.println("Enter Car Colour TO Delete");
		String color = scanner.next();
		try {
			openConnection();
			quary = " delete from  car where Colour=?";
			preparedStatement = connection.prepareStatement(quary);
			preparedStatement.setString(1, color);
			int row = preparedStatement.executeUpdate();
			if (row != 0) {
				System.out.println(row + " deleted");
			} else {
				System.out.println("===CAR NOT FOUND===");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" ");
	}

//-----------------------------Update car--------------------------------------------------------------	
	public void updateCar(Scanner scanner) {
		boolean take = true;
			try {
					System.out.println("ENTER CAR ID TO UPDATE");
					int id=scanner.nextInt();
					checkID(id);
				    if (resultSet1.next()) {
				    	System.out.print(resultSet1.getInt(1) + " ");
						System.out.print(resultSet1.getString(2) + " ");
						System.out.print(resultSet1.getString(3) + " ");
						System.out.print(resultSet1.getString(4) + " ");
						System.out.print(resultSet1.getInt(5) + " ");
						System.out.print(resultSet1.getString(6) + " ");
						System.out.println(resultSet1.getString(7) + " ");
						System.out.println(" ");
				    	while (take == true) {	
				    	      System.out.println( "1.Update Car Name\n" + "2.Update Car Model\n"
								                  + "3.Update Car Brand\n" + "4.Update Car Price\n"
				    	    		              + "5.Update Car Colour\n" + "6.Go Back");
						      System.out.println("SELECT ONE OPTION: ");
						      int update = scanner.nextInt();
						      switch (update) {
						      case 1:
							         updateCarName(id, scanner);
							         break;
						      case 2:
							         updateCarModel(id,scanner);
							         break;
						      case 3:
							         updateCarBrand(id,scanner);
							         break;
						      case 4:
						           	updateCarPrice(id,scanner);
							         break;
						      case 5:
							        updateCarColour(id,scanner);
							        break;
						      case 6:
							        take=false;
							        return;
						      default:
						            System.out.println("Invalid Input  Plz Try Again ");
				              }					
				       }
			      }else {
				     System.out.println("====CAR NOT FOUND====");
			        }
		  } catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
		
	//------------------------Update Name-----------------------------
	private void updateCarName(int id,Scanner scanner) {
		try {
			openConnection();
			scanner.nextLine();
			int id1=id;
			System.out.println("ENTER NEW CAR NAME");
			String name=scanner.nextLine();
			quary=" update car set name=? where id=?";
			preparedStatement=connection.prepareStatement(quary);
			preparedStatement.setString(1,name );
			preparedStatement.setInt(2, id1);
		    preparedStatement.executeUpdate();
		    System.out.println("Car Name Update"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	


	private void updateCarModel(int id, Scanner scanner) {
		try {
			openConnection();
			scanner.nextLine();
			int id1=id;
			System.out.println("ENTER NEW CAR MODEL NAME");
			String model=scanner.nextLine();
			quary=" update car set model=? where id=?";
			preparedStatement=connection.prepareStatement(quary);
			preparedStatement.setString(1,model );
			preparedStatement.setInt(2, id1);
		    preparedStatement.executeUpdate();
		    System.out.println("Car Name Update"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}


	private void updateCarBrand(int id, Scanner scanner) {
		try {
			openConnection();
			scanner.nextLine();
			int id1=id;
			System.out.println("ENTER NEW CAR BRAND NAME");
			String brand=scanner.nextLine();
			quary=" update car set brand=? where id=?";
			preparedStatement=connection.prepareStatement(quary);
			preparedStatement.setString(1,brand );
			preparedStatement.setInt(2, id1);
		    preparedStatement.executeUpdate();
		    System.out.println("Car Name Update"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}


	private void updateCarPrice(int id, Scanner scanner) {
		try {
			openConnection();
			scanner.nextLine();
			int id1=id;
			System.out.println("ENTER NEW CAR PRICE");
			int price=scanner.nextInt();
			quary=" update car set price=? where id=?";
			preparedStatement=connection.prepareStatement(quary);
			preparedStatement.setInt(1,price );
			preparedStatement.setInt(2, id1);
		    preparedStatement.executeUpdate();
		    System.out.println("Car Name Update"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	private void updateCarColour(int id, Scanner scanner) {
		try {
			openConnection();
			scanner.nextLine();
			int id1=id;
			System.out.println("ENTER NEW CAR COLOR");
			String color=scanner.nextLine();
			quary=" update car set colour=? where id=?";
			preparedStatement=connection.prepareStatement(quary);
			preparedStatement.setString(1,color );
			preparedStatement.setInt(2, id1);
		    preparedStatement.executeUpdate();
		    System.out.println("Car Name Update"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	private static void openConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weja4", "root", "root");
	}

	private static void closeConnection() throws SQLException {
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}

	}

}
