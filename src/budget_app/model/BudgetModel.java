package budget_app.model;

import budget_app.data.BudgetAllocation;
import budget_app.data.User;

import java.sql.*;
import java.util.Scanner;

public class BudgetModel {
    static Scanner scanner = new Scanner(System.in);
    static User user = new User();
    static BudgetAllocation budgetAllocation = new BudgetAllocation();

    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;

    static String connectionString = "jdbc:mysql://localhost/MyBudgetApp?"
            + "user=root&password=coddingnomads"
            + "&useSSL=false&allowPublicKeyRetrieval=true";

    //method to create a new user.
    public static void createUser(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO MyBudgetApp.User" +
                    "(UserName, Amount) VALUES (?, ?)");
            System.out.print("Set name: ");
            user.setName(scanner.next());
            System.out.print("Set amount: ");
            user.setAmount(scanner.nextDouble());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDouble(2, user.getAmount());
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //method to create a new budget for a user.
    public static void createBudget(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO MyBudgetApp.BudgetedItems" +
                    "(UserId, Groceries, Housing, BasicUtilities, Transport, Insurance) VALUES (?, ?, ?, ?, ?, ?)");
            System.out.print("Use your user Id here: ");
            preparedStatement.setDouble(1, scanner.nextInt());
            System.out.println("Budget allocation for groceries: ");
            budgetAllocation.setGroceries(scanner.nextDouble());
            System.out.println("Budget allocation for housing: ");
            budgetAllocation.setHousing(scanner.nextDouble());
            System.out.println("Budget allocation for basic utilities(internet, water, electricity): ");
            budgetAllocation.setBasicUtilities(scanner.nextDouble());
            System.out.println("Budget allocation for transport: ");
            budgetAllocation.setTransport(scanner.nextDouble());
            System.out.println("Budget allocation for insurance: ");
            budgetAllocation.setInsurance(scanner.nextDouble());
            preparedStatement.setDouble(2, budgetAllocation.getGroceries());
            preparedStatement.setDouble(3, budgetAllocation.getHousing());
            preparedStatement.setDouble(4, budgetAllocation.getBasicUtilities());
            preparedStatement.setDouble(5, budgetAllocation.getTransport());
            preparedStatement.setDouble(6, budgetAllocation.getInsurance());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewName(){
        System.out.println("Enter your name please: ");
        String uName = scanner.next();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT UserName FROM MyBudgetApp.User WHERE Username = ?");
            preparedStatement.setString(1, uName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String userName = resultSet.getString("UserName");
                //a condition to determine if the user is there using UserName
                if (uName.equals(userName)){
                    System.out.println("Welcome back: " + uName);
                    user.viewBudget();
                } else{
                    //if user not available, redirect back to login page.
                    System.out.println("Incorrect username, login again.");
                    user.login();
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void viewProfile(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM MyBudgetApp.User ORDER BY DateRegistered DESC LIMIT 1");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt("UserId");
                String userName = resultSet.getString("UserName");
                double amount = resultSet.getDouble("Amount");

                System.out.println("Your userId is: " + userId);
                System.out.println("Your user name is: " + userName);
                System.out.println("Your balance is: " + amount);

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to query budgeted items and there prices in the database

    public static void viewItems(){
        System.out.print("Please enter your user id: ");
        int userId = scanner.nextInt();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM MyBudgetApp.BudgetedItems WHERE UserId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                //using a while loop to go through all the items within the BudgetedItems table
                int UserId = resultSet.getInt("UserId");
                double groceries = resultSet.getDouble("Groceries");
                double housing = resultSet.getDouble("Housing");
                double basicUtilities  = resultSet.getDouble("BasicUtilities");
                double transport = resultSet.getDouble("Transport");
                double insurance = resultSet.getDouble("Insurance");

                if (UserId == userId){
                    System.out.println("Your budget for groceries is: " + groceries);
                    System.out.println("Your budget for housing is: " + housing);
                    System.out.println("Your budget for basic utilities is: " + basicUtilities);
                    System.out.println("Your budget for transport is: " + transport);
                    System.out.println("Your budget for insurance is: " + insurance);
                } else {
                    System.out.println("Enter correct userId");
                    user.viewBudget();

                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //method to update user information like name and salary
    public static void updateUser(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("UPDATE MyBudgetApp.User" +
                    "SET Amount = ? WHERE UserName = ?");
            preparedStatement.setDouble(1, scanner.nextDouble());
            preparedStatement.setString(2, scanner.next());
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to update items budget price
    public static void updateItems(){}

    //method to delete user
    public static void deleteUser(){}

    //method to delete items
    public static void deleteItems(){}
}
