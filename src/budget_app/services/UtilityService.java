package budget_app.services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UtilityService {

    Scanner scanner = new Scanner(System.in);

    public static void printApplicationTitle(){
        System.out.println("BudgetBuddy");
    }

    public static void printFarewell() {
        System.out.println("So long! Farewell! Until we meet again!");
    }

    public static void printMainMenu() {
        System.out.println("\n*************** MAIN MENU *****************\n");

        System.out.println("1. View Your Budget");
        System.out.println("2. Allocate Budget");
        System.out.println("3. Update Your Budget");
        System.out.println("4. Delete Your Budget");
        System.out.println("5. Delete Your Account");
        System.out.println("6. Quit Application");
        System.out.println();
    }
}
