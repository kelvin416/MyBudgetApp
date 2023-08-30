package budget_app.model;

import budget_app.data.BudgetAllocation;
import budget_app.data.User;

import java.sql.*;

public class BudgetModel {
    static User user = new User();
    static BudgetAllocation budget = new BudgetAllocation();

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
            preparedStatement.setString(1, user.getName());
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
            preparedStatement.setDouble(1, budget.getGroceries());
            preparedStatement.setDouble(2, budget.getHousing());
            preparedStatement.setDouble(3, budget.getBasicUtilities());
            preparedStatement.setDouble(4, budget.getTransport());
            preparedStatement.setDouble(5, budget.getInsurance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
