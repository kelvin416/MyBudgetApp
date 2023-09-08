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
        System.out.println("Update your budget allocation? (Y for Yes or N for NO)");
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
        System.out.println("Hello here is your budgeted items.");
        budgetModel.viewItems(userId);

        System.out.println("Delete budgeted items?");
        System.out.print("YES/NO: ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("YES")){
            budgetModel.deleteBudgetAllocation();
        } else {
            homePage();
        }
    }

    public static void updateInfo(int userId){
        budgetModel.updateUser(userId);
    }

    public static void updateAllocation(){

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
