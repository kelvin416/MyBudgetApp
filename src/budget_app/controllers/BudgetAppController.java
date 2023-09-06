package budget_app.controllers;

import budget_app.data.BudgetAllocation;
import budget_app.data.User;
import budget_app.model.BudgetModel;

import java.util.Scanner;

public class BudgetAppController {
    public static void main(String[] args) {
        User user = new User();
        BudgetModel budgetModel = new BudgetModel();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Keltich Budgeting App");
        System.out.print("Are you a new user? (Y for Yes, N for No): ");
        String response = scanner.next();

        if (response.toUpperCase().equals("Y")){
            //register method for new users.
            user.register();
        } else if (response.toUpperCase().equals("N")) {
            //login method for registered users
            user.login();
        } else {
            System.out.println("Type in the correct response: ");
        }
        System.out.println();
        scanner.close();
    }
}
