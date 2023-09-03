package budget_app.data;

import budget_app.model.BudgetModel;

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

    public void register(){
        System.out.println("Please register here: ");
        //budget.createUser method will save the data in MyBudgetApp DB
        budgetModel.createUser();
        //view user
        viewUser();

        allocateBudget();

    }

    public void login(){
        budgetModel.viewName();

    }

    public static void viewUser(){

        budgetModel.viewProfile();
    }

    public void allocateBudget(){

        budgetModel.createBudget();
    }

    public void viewBudget(int userId){
        budgetModel.viewItems(userId);
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
