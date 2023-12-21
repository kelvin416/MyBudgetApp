package budget_app.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseParent {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    static String connectionString = "jdbc:mysql://localhost/My_BudgetApp?"
            + "user=root&password=codingnomads"
            + "&useSSL=false&allowPublicKeyRetrieval=true";
}
