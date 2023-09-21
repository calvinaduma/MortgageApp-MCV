package cpsc2150.banking.views;

import cpsc2150.banking.controllers.IMortgageController;

import java.util.*;

public class MortgageView implements IMortgageView {

    Scanner scanner = new Scanner(System.in);
    IMortgageController controller;

    /**
     * This is a default constructor
     */
    public MortgageView() {
        ;
    }

    public void setController(IMortgageController c) {
        controller = c;
    }

    public double getHouseCost() {
        System.out.println("How much does the house cost?");
        int houseCost = scanner.nextInt();
        // whitespace is to get rid of the next line carraige space after inputting an integer
        String whitespace = scanner.nextLine();
        return houseCost;
    }

    public double getDownPayment() {
        System.out.println("How much is the down payment?");
        int downPayment = scanner.nextInt();
        // whitespace is to get rid of the next line carraige space after inputting an integer
        String whitespace = scanner.nextLine();
        return downPayment;
    }

    public int getYears() {
        System.out.println("How many years?");
        int numYears = scanner.nextInt();
        String whitespace = scanner.nextLine();
        return numYears;
    }

    public double getMonthlyDebt() {
        System.out.println("How much are your monthly debt payments?");
        int monthlyDebt = scanner.nextInt();
        // whitespace is to get rid of the next line carraige space after inputting an integer
        String whitespace = scanner.nextLine();
        return monthlyDebt;
    }

    public double getYearlyIncome() {
        System.out.println("How much is your yearly income?");
        int yearlyIncome = scanner.nextInt();
        // whitespace is to get rid of the next line carraige space after inputting an integer
        String whitespace = scanner.nextLine();
        return yearlyIncome;
    }

    public int getCreditScore() {
        System.out.println("What is your credit score?");
        int creditScore = scanner.nextInt();
        // whitespace is to get rid of the next line carraige space after inputting an integer
        String whitespace = scanner.nextLine();
        return creditScore;
    }

    public String getName() {
        System.out.println("What's your name?");
        return scanner.nextLine();
    }

    public void printToUser(String s) {
        System.out.println(s);
    }

    public void displayPayment(double p) {
        System.out.println(p);
    }

    public void displayRate(double r) {
        System.out.println(r);
    }

    public void displayApproved(boolean a) {
        System.out.println(a);
    }

    public boolean getAnotherMortgage() {
        System.out.println("Would you like to apply for another mortgage? Y/N");
        char decision = scanner.nextLine().charAt(0);
        // loop to check input is correct
        boolean loop = true;
        while (loop) {
            if (decision == 'Y' || decision == 'y' || decision == 'N' || decision == 'n') {
                break;
            } else {
                System.out.print("Please enter Y/y or N/n: ");
                decision = scanner.nextLine().charAt(0);
            }
        }
        if (decision == 'Y' || decision == 'y') {
            return true;
        } else {
            return false;
        }
    }

    public boolean getAnotherCustomer() {
        System.out.println("Would you like to enter another customer? Y/N");
        char decision = scanner.nextLine().charAt(0);
        boolean loop = true;
        // loop to check input
        while (loop) {
            if (decision == 'Y' || decision == 'y' || decision == 'N' || decision == 'n') {
                break;
            } else {
                System.out.print("Please enter Y/y or N/n: ");
                decision = scanner.nextLine().charAt(0);
            }
        }
        if (decision == 'Y' || decision == 'y') {
            return true;
        } else {
            return false;
        }
    }
}
