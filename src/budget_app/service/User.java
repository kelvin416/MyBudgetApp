package budget_app.service;

import budget_app.database.BudgetModel;

import java.util.Scanner;

public class User {
    BudgetAllocation budgetAllocation = new BudgetAllocation();
    static BudgetModel budgetModel = new BudgetModel();
    private String name;
    private int userId;
    private double amount;

    Scanner scanner = new Scanner(System.in);


    public User() {
    }

    public User(String name, int userId, double amount) {
        this.name = name;
        this.userId = userId;
        this.amount = amount;
    }

    public static void homePage(){
        System.out.println("Kelotich Budget App");
    }

    public void register(){
        System.out.println("Please register here: ");
        //budget.createUser method will save the data in MyBudgetApp DB
        budgetModel.createUser();
        //view user
        viewUser();

        allocateBudget();

    }

    //using the login method
    public void login(){
        budgetModel.viewName();
        System.out.print("Update your budget allocation? (Y for Yes or N for NO): ");
        String update = scanner.next();
        if (update.toUpperCase().equals("Y")){
            updateInfo(userId);
        } else {
            return;
        }

    }

    public static void viewUser(){

        budgetModel.viewProfile();
    }

    public void allocateBudget(){

        budgetModel.createBudget();
    }

    public void viewBudget(int userId){

        budgetModel.viewItems(userId);

        System.out.println("Delete budgeted items?");
        System.out.print("(Y for Yes, N for No): ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("Y")){
            budgetModel.deleteBudgetAllocation();
        } else {
            homePage();
        }
    }

    public static void updateInfo(int userId){
        budgetModel.updateUser(userId);
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", amount=" + amount +
                '}';
    }
}