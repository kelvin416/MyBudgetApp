package budget_app.database;

import budget_app.service.BudgetAllocation;
import budget_app.service.User;

import java.sql.*;
import java.util.InputMismatchException;
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
        } catch (InputMismatchException e){
            System.out.println("Please enter a number");
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
            System.out.print("Budget allocation for groceries: ");
            budgetAllocation.setGroceries(scanner.nextDouble());
            System.out.print("Budget allocation for housing: ");
            budgetAllocation.setHousing(scanner.nextDouble());
            System.out.print("Budget allocation for basic utilities(internet, water, electricity): ");
            budgetAllocation.setBasicUtilities(scanner.nextDouble());
            System.out.print("Budget allocation for transport: ");
            budgetAllocation.setTransport(scanner.nextDouble());
            System.out.print("Budget allocation for insurance: ");
            budgetAllocation.setInsurance(scanner.nextDouble());
            preparedStatement.setDouble(2, budgetAllocation.getGroceries());
            preparedStatement.setDouble(3, budgetAllocation.getHousing());
            preparedStatement.setDouble(4, budgetAllocation.getBasicUtilities());
            preparedStatement.setDouble(5, budgetAllocation.getTransport());
            preparedStatement.setDouble(6, budgetAllocation.getInsurance());
            preparedStatement.execute();
        } catch (InputMismatchException e){
            System.out.println("Please enter a number");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewName(){
        System.out.println("Enter your name please as registered: ");
        String uName = scanner.next();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT UserId, UserName FROM MyBudgetApp.User WHERE Username = ?");
            preparedStatement.setString(1, uName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String userName = resultSet.getString("UserName");
                int userId = resultSet.getInt("UserId");
                //a condition to determine if the user is there using UserName
                if (uName.equalsIgnoreCase(userName)){
                    System.out.println("Welcome back " + uName);
                    user.viewBudget(userId);
                } else {
                    //if user not available, redirect back to login page.
                    System.out.println("Incorrect username, login again.");
                    user.login();
                }
            }
        } catch (InputMismatchException e){
            System.out.println("Please enter a number");
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
                System.out.println("Your budget amount is: " + amount);

            }
        } catch (InputMismatchException e){
            System.out.println("Please enter a number");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to query budgeted items and there prices in the database

    public static void viewItems(int Uid){
        System.out.print("Please enter your user id: ");
        int userId = scanner.nextInt();
        System.out.println(userId);
        System.out.println(Uid);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM MyBudgetApp.BudgetedItems WHERE UserId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if(Uid != userId) {
                System.out.println("Enter correct userId or you don't have budget allocated.");
                System.out.print("Would you like to allocate your budget(Y for Yes and N for NO): ");
                String response2 = scanner.next();
                if (response2.toUpperCase().equals("Y")){
                    user.allocateBudget();
                } else if (response2.toUpperCase().equals("N")) {
                    System.out.println("Would you like to go back to login page or exit?(type: (login or exit))");
                    System.out.println("Hit CRTL-D to exit the app.");
                    String response3 = scanner.next();
                    if (response3.toUpperCase().equals("LOGIN")){
                        user.login();
                    }
                } else {
                    user.viewUser();
                }
            }
            if (Uid == userId){
                while (resultSet.next()) {
                    //using a while loop to go through all the items within the BudgetedItems table
//                int UserId = resultSet.getInt("UserId");
//                    if (Uid == userId) {
                    double groceries = resultSet.getDouble("Groceries");
                    double housing = resultSet.getDouble("Housing");
                    double basicUtilities = resultSet.getDouble("BasicUtilities");
                    double transport = resultSet.getDouble("Transport");
                    double insurance = resultSet.getDouble("Insurance");

                    System.out.println("Hello here is your budgeted items.");
                    System.out.println("Your budget for groceries is: " + groceries);
                    System.out.println("Your budget for housing is: " + housing);
                    System.out.println("Your budget for basic utilities is: " + basicUtilities);
                    System.out.println("Your budget for transport is: " + transport);
                    System.out.println("Your budget for insurance is: " + insurance);
//                    }
                }

                }
        }  catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (InputMismatchException e){
            System.out.println("Please enter a number");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //method to update user information like name and salary
    public static void updateUser(int Uid){
        System.out.println("What is your user id: ");
        int userId = scanner.nextInt();
        System.out.println("How much would you like your updated budget to be: ");
        double updateAmount = scanner.nextDouble();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("UPDATE MyBudgetApp.User SET Amount = ? WHERE ( UserId = ? )");
            preparedStatement.setDouble(1, updateAmount);
            preparedStatement.setInt(2, Uid);
            if (Uid == userId){
                System.out.println(Uid);
                preparedStatement.execute();

                System.out.println("Your Updated Budget Allocation is: ");
                viewProfile();
            } else {
                System.out.println("Failed to update your budget allocation.");
                viewProfile();
            }

        } catch (InputMismatchException e){
            System.out.println("Please enter a number");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //method to delete user
    public static void deleteUser(){
        System.out.println("Enter your user Id to confirm deletion: ");
        int userId = scanner.nextInt();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("DELETE FROM MyBudgetApp.User WHERE UserId = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            System.out.println("Account deletion was a success.");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //method to delete items
    public static void deleteBudgetAllocation(){
        System.out.println("Enter your user id to delete your budget allocation: ");
        int userId = scanner.nextInt();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("DELETE FROM MyBudgetApp.BudgetedItems WHERE UserId = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}