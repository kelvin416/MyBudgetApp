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
        System.out.println("Welcome to Kelotich Budgeting App");
        System.out.println("Are you a new user? (Y for Yes, N for No)");
        String response = scanner.next();

        if (response.toUpperCase().equals("Y")){
            user.register();
        } else if (response.toUpperCase().equals("N")) {
            //log into budget allocation
            user.login();
        } else {
            System.out.println("Type in the correct response: ");
        }
        System.out.println();
        scanner.close();
    }
}
