package budget_app.services;

import budget_app.database.BudgetMapper;
import budget_app.database.UserMapper;
import budget_app.model.BudgetAllocationModel;
import budget_app.model.UserModel;

import java.util.Scanner;

public class BudgetService {
    BudgetMapper budgetMapper = new BudgetMapper();
    UserMapper userMapper = new UserMapper();

    public void viewBudget(UserModel user){
        Scanner scanner = new Scanner(System.in);
        budgetMapper.viewBudgetItems(user);
    }

    public void allocateBudget(UserModel user){
        budgetMapper.createBudget(user);
    }

    public void deleteBudget(UserModel user){
        budgetMapper.deleteBudgetAllocation(user);
    }

    public void updateBudget(UserModel user) {

        boolean valid = false;
        double budgetTmp = 0;
        Scanner scanner = new Scanner(System.in);

        while (valid == false) {
            try {

                System.out.println("How much would you like your updated budget to be: ");
                budgetTmp = Double.parseDouble(scanner.nextLine());
                user.setTotalBudgetAmount(budgetTmp);
                valid = true;
            } catch (Exception e) {
                System.out.println("Please try again - enter a valid number");
                scanner.next();
            }
        }

        userMapper.updateUserBudget(user);
    }
}
