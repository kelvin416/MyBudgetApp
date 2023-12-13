package budget_app.controller;

import budget_app.model.UserModel;
import budget_app.database.BudgetMapper;
import budget_app.services.BudgetService;
import budget_app.services.UserService;
import budget_app.services.UtilityService;

import java.util.Scanner;

public class BudgetAppController {

    UserService userService = new UserService();
    BudgetService budgetService = new BudgetService();
    public void start() {
        UtilityService.printApplicationTitle();
        applicationLoop();
        UtilityService.printFarewell();
    }

    public void applicationLoop(){
        UserModel user = null;

        try {
            user = userService.loginOrRegister();
        } catch (Exception e){
            System.out.println("Sorry, we could not log you in.");
            return;
        }

        boolean keepRunning = true;

        while (keepRunning){
            UtilityService.printMainMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.println("What would you like to do? Please enter the number of your choice.");
            int userChoice = Integer.parseInt(scanner.nextLine());

            try {
                switch (userChoice) {
                    case 1:
                        userService.viewUser(user);
                        break;
                    case 2:
                        budgetService.allocateBudget(user);
                        break;
                    case 3:
                        budgetService.updateBudget(user);
                        break;
                    case 4:
                        budgetService.deleteBudget(user);
                        break;
                    case 5:
                        userService.deleteAccount(user);
                        break;
                    case 6:
                        return;
                    // you could keep adding more options here
                    // just be sure to also add them the main menu as well (UtilityService.printMainMenu();)

                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
    }
}
