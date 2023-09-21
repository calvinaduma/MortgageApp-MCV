package cpsc2150.banking.controllers;

import cpsc2150.banking.controllers.IMortgageController;
import cpsc2150.banking.views.*;
import cpsc2150.banking.models.*;

public class MortgageController implements IMortgageController {
    /**
     * @invariant: view contains information about customer and mortgage
     */
    private IMortgageView view;
    int MAX_CREDIT_SCORE = 850;
    /**
     * This creates an object that interacts with user to get the customer's info and their mortgage.
     * @param object
     */
    public MortgageController(IMortgageView object){
        view = object;
    }

    public void submitApplication(){
        boolean anotherCustomer = true;
        while (anotherCustomer){
            boolean anotherMortgage = true;
            String name = view.getName();
            double income = view.getYearlyIncome();
            while (income <= 0) {
                view.printToUser("Income must be greater than 0.");
                income = view.getYearlyIncome();
            }
            double monthlyDebt = view.getMonthlyDebt();
            while (monthlyDebt < 0) {
                view.printToUser("Debt must be greater than or equal to 0");
                monthlyDebt = view.getMonthlyDebt();
            }
            int creditScore = view.getCreditScore();
            while (creditScore <= 0 || creditScore >= MAX_CREDIT_SCORE) {
                view.printToUser("Credit Score must be greater than 0 and less than 850.");
                creditScore = view.getCreditScore();
            }
            while (anotherMortgage) {
                double houseCost = view.getHouseCost();
                while (houseCost <= 0) {
                    view.printToUser("Cost must be greater than 0.");
                    houseCost = view.getHouseCost();
                }
                double downPayment = view.getDownPayment();
                while (downPayment <= 0 || downPayment > houseCost) {
                    view.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
                    downPayment = view.getDownPayment();
                }
                int years = view.getYears();
                while (years <= 0) {
                    view.printToUser("Years must be greater than 0.");
                    years = view.getYears();
                }
                // creation of customer and mortgage plan
                ICustomer customer = new Customer(monthlyDebt, income, creditScore, name);
                IMortgage customerMortgage = new Mortgage(houseCost, downPayment, years, customer);
                customer.applyForLoan(downPayment, houseCost, years);
                // prints user data and mortgage if loan was approved
                view.printToUser(customer.toString());
                // asks for another mortgage
                anotherMortgage = view.getAnotherMortgage();
                if (!anotherMortgage){
                    break;
                }
            }
            // asks for another customer
            anotherCustomer = view.getAnotherCustomer();
            if (!anotherCustomer){
                break;
            }
        }
    }
}
