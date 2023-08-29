package budget_app.controllers;

import budget_app.data.BudgetAllocation;
import budget_app.data.User;

import java.util.Scanner;

public class BudgetAppController {
    public static void main(String[] args) {
        User user = new User();
        BudgetAllocation buget = new BudgetAllocation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Kelotich Budgeting App");
        System.out.println("Are you a new user? (Y for Yes, N for No)");
        String response = scanner.next();

        if (response.toUpperCase().equals("Y")){
            System.out.print("Set name: ");
            user.setName(scanner.next());
            System.out.print("Set id: ");
            user.setUserId(scanner.nextInt());
            System.out.print("Set amount: ");
            user.setAmount(scanner.nextInt());
        } else if (response.toUpperCase().equals("N")) {
            //log into budget allocation
            user.login();
        } else {
            System.out.println("Type in the correct response: ");
        }
        scanner.close();
    }
}
