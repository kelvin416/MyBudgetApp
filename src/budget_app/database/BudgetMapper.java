package budget_app.database;

import budget_app.model.BudgetAllocationModel;
import budget_app.model.UserModel;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BudgetMapper extends DatabaseParent {

    BudgetAllocationModel budgetAllocation = new BudgetAllocationModel();
    Scanner scanner = new Scanner(System.in);

    //method to create a new budget for a user.
    public void createBudget(UserModel user){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO MyBudgetApp.BudgetedItems" +
                    "(UserId, Groceries, Housing, BasicUtilities, Transport, Insurance) VALUES (?, ?, ?, ?, ?, ?)");
            //System.out.print("Use your user Id here: ");

            System.out.print("Budget allocation for groceries: ");
            String g = scanner.nextLine();
            double groceries = Double.parseDouble(g);
            budgetAllocation.setGroceries(groceries);
            System.out.print("Budget allocation for housing: ");
            budgetAllocation.setHousing(Double.parseDouble(scanner.nextLine()));
            System.out.print("Budget allocation for basic utilities(internet, water, electricity): ");
            budgetAllocation.setBasicUtilities(Double.parseDouble(scanner.nextLine()));
            System.out.print("Budget allocation for transport: ");
            budgetAllocation.setTransport(Double.parseDouble(scanner.nextLine()));
            System.out.print("Budget allocation for insurance: ");
            budgetAllocation.setInsurance(Double.parseDouble(scanner.nextLine()));

            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setDouble(2, budgetAllocation.getGroceries());
            preparedStatement.setDouble(3, budgetAllocation.getHousing());
            preparedStatement.setDouble(4, budgetAllocation.getBasicUtilities());
            preparedStatement.setDouble(5, budgetAllocation.getTransport());
            preparedStatement.setDouble(6, budgetAllocation.getInsurance());

            preparedStatement.execute();

            user.setBudget(budgetAllocation);

            //scanner.next();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    //Method to query budgeted items and there prices in the database

    public void viewBudgetItems(UserModel user){
        boolean budgetInfo = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM MyBudgetApp.BudgetedItems WHERE UserId = ?");
            preparedStatement.setInt(1, user.getUserId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
                budgetInfo = true;
            }
        }  catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        } catch (InputMismatchException e){
            System.out.println("Please enter a number");
        }

        if (budgetInfo == false){
            System.out.println("No budget information to show.");
        }
    }

    //method to delete items
    public void deleteBudgetAllocation(UserModel user){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("DELETE FROM MyBudgetApp.BudgetedItems WHERE UserId = ?");
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.execute();
            System.out.println("** Budget Deleted **");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
