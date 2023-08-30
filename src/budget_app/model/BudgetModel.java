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

    public static void createUser(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = "jdbc:mysql://localhost/MyBudgetApp?"
                    + "user=root&password=coddingnomads"
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO MyBudgetApp.User" +
                    "(UserName, Amount) VALUES (?, ?)");
            System.out.print("Set name: ");
            user.setName(scanner.next());
            System.out.print("Set amount: ");
            user.setAmount(scanner.nextDouble());
            System.out.println(user.getName());
            preparedStatement.setString(1, user.getName().toString());
            preparedStatement.setDouble(2, user.getAmount());
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createBudget(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = "jdbc:mysql://localhost/MyBudgetApp?"
                    + "user=root&password=coddingnomads"
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO MyBudgetApp.ItemsToBudget" +
                    "(Groceries, Housing, BasicUtilities, Transport, Insurance) VALUES (?, ?, ?, ?, ?)");
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
            preparedStatement.setDouble(1, budgetAllocation.getGroceries());
            preparedStatement.setDouble(2, budgetAllocation.getHousing());
            preparedStatement.setDouble(3, budgetAllocation.getBasicUtilities());
            preparedStatement.setDouble(4, budgetAllocation.getTransport());
            preparedStatement.setDouble(5, budgetAllocation.getInsurance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
