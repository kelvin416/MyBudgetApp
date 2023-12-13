package budget_app.model;

import java.util.Scanner;

public class UserModel {
    private String name;
    private int userId;
    private double totalBudgetAmount;

    private BudgetAllocationModel budget;

    public UserModel() {
    }

    public UserModel(String name, int userId, double amount) {
        this.name = name;
        this.userId = userId;
        this.totalBudgetAmount = amount;
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

    public double getTotalBudgetAmount() {
        return totalBudgetAmount;
    }
    public void setTotalBudgetAmount(double totalBudgetAmount) {
        this.totalBudgetAmount = totalBudgetAmount;
    }

    public BudgetAllocationModel getBudget() {
        return budget;
    }

    public void setBudget(BudgetAllocationModel budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", amount=" + totalBudgetAmount +
                '}';
    }
}
