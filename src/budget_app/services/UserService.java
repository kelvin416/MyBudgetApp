package budget_app.services;

import budget_app.database.BudgetMapper;
import budget_app.database.UserMapper;
import budget_app.model.UserModel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserService {

    Scanner scanner = new Scanner(System.in);
    UserMapper userMapper = new UserMapper();
    BudgetMapper budgetMapper = new BudgetMapper();
    public UserModel registerUser(){
        System.out.println("Great, let's get you registered. ");
        UserModel user = new UserModel();
        System.out.print("What is your name? ");
        user.setName(scanner.next());
        System.out.print("What is your total target budget? ");

        boolean validNumber = false;
        double budgetAmount = 0.0;

        while (validNumber == false){
            try {
                budgetAmount = scanner.nextDouble();
                validNumber = true;
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid number. ");
                scanner.next();
            }
        }
        user.setTotalBudgetAmount(budgetAmount);

        userMapper.createUser(user);
        int userId = userMapper.getUserByName(user.getName());
        user.setUserId(userId);
        return user;
    }

    public UserModel viewUser(UserModel user) throws Exception {

        UserModel updatedUser = userMapper.refreshProfileInfo(user);

        System.out.println("\n************** USER PROFILE INFO ***************");
        System.out.println("Your userId is: " + updatedUser.getUserId());
        System.out.println("Your user name is: " + updatedUser.getName());
        System.out.println("Your budget amount is: " + updatedUser.getTotalBudgetAmount());

        budgetMapper.viewBudgetItems(user);

        return updatedUser;
    }

    public UserModel loginOrRegister() throws Exception {
        Scanner scanner = new Scanner(System.in);
        UserModel user;
        System.out.print("Are you a new user? (Y for Yes, N for No): ");
        String response = scanner.next();

        while (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N")){
            System.out.print("Please enter a valid response. Are you a new user? (Y for Yes, N for No): ");
            response = scanner.next();
        }

        if (response.toUpperCase().equals("Y")){
            //register method for new users.
            user = registerUser();
        } else {
            //login method for registered users
            System.out.print("What name did you register with? ");
            String name = scanner.next();
            user = userMapper.login(name);
        }
        return user;
    }

    public void deleteAccount(UserModel user) {
        userMapper.deleteUser(user);
    }
}
