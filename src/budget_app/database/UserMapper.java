package budget_app.database;

import budget_app.model.UserModel;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class UserMapper extends DatabaseParent {

    //method to create a new user.
    public void createUser(UserModel user){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("INSERT INTO My_BudgetApp.User" +
                    "(UserName, Amount) VALUES (?, ?)");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setDouble(2, user.getTotalBudgetAmount());
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserModel login(String uName) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT UserId, UserName, Amount FROM My_BudgetApp.User WHERE Username = ?");
            preparedStatement.setString(1, uName);
            resultSet = preparedStatement.executeQuery();
            boolean couldLogin = false;

            while (resultSet.next()){
                String userName = resultSet.getString("UserName");
                int userId = resultSet.getInt("UserId");
                double amount = resultSet.getDouble("Amount");

                UserModel user = new UserModel(userName, userId, amount);
                System.out.println("Welcome back " + user.getName() + "!");
                couldLogin = true;
                return user;
            }

            if (couldLogin == false){
                System.out.println("Incorrect username, login again.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        throw new Exception("Could not login. Please try again.");
    }

    public UserModel refreshProfileInfo(UserModel user) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT * FROM My_BudgetApp.User WHERE UserName = ? ORDER BY DateRegistered DESC LIMIT 1");
            preparedStatement.setString(1, user.getName());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt("UserId");
                String userName = resultSet.getString("UserName");
                double amount = resultSet.getDouble("Amount");

                UserModel userUpdated = new UserModel(userName, userId, amount);
                return userUpdated;


            }
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        throw new Exception("Could not refresh profile info");
    }

    //method to update user information like name and salary
    public void updateUserBudget(UserModel user){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("UPDATE My_BudgetApp.User SET Amount = ? WHERE ( UserId = ? )");
            preparedStatement.setDouble(1, user.getTotalBudgetAmount());
            preparedStatement.setInt(2, user.getUserId());
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(UserModel user){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("DELETE FROM My_BudgetApp.User WHERE UserId = ?");
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.execute();
            System.out.println("Account deletion was a success.");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserByName(String name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString);
            preparedStatement = connection.prepareStatement("SELECT UserId FROM My_BudgetApp.User WHERE UserName = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("UserId");
                return id;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("error loading user");
    }
}
