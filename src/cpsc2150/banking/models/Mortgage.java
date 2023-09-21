package cpsc2150.banking.models;

import java.lang.Math;

public class Mortgage extends AbsMortgage {
    /**
     * @invariant: 0 <= downPayment < costOfHome &&
     *             year = 15 || year = 30        &&
     *             costOfHome >= 0               &&
     *             customerPerson [exists]       &&
     *             0 <= rate <= 1                &&
     *             debtToIncomeRatio > 0         &&
     *             0 <= percentDown < 1          &&
     *             0 < payment <= costOfHome     &&
     *             0 <= principal < costOfHome   &&
     *             0 < debtToIncomeRatio < 1
     *
     * Correspondence: self.downPayment = downPayment && self.year = year                        &&
     *                 self.costOfHome = costOfHome   && self.customer = customer                &&
     *                 self.Rate = rate
     */

    int PERCENT_CONVERSION = 100;
    int MONTHS_IN_A_YEAR = 12;
    private double downPayment;
    private int year;
    private ICustomer customer;
    private double costOfHome;
    private double rate;
    private double percentDown;
    private double payment;
    private double principal;
    private double debtToIncomeRatio;


    /**
     * This creates an object to keep track of a customer who takes out a mortgage
     *
     * @param costH = the cost of the customer's home
     * @param downP = the customer's down payment for the mortgage
     * @param years = the number of years the customer opted to pay for their mortgage
     * @param customerPerson = the customer's identity
     *
     * @pre: costH >= 0 && 0 <= downP < costH && years=15 or years=30 && customerPerson [exists]
     * @post: costOfHome = costH && downPayment = downP && year = years && customer = customerPerson
     */
    public Mortgage(double costH, double downP, int years, ICustomer customerPerson){
        costOfHome = costH;
        downPayment = downP;
        year = years;
        customer = customerPerson;
        rate = BASERATE;
        percentDown = (downPayment/costOfHome)*PERCENT_CONVERSION;

        // Rate
        if (years < MAX_YEARS) {
            rate+= GOODRATEADD;
        } else {
            rate += NORMALRATEADD;
        }
        if (percentDown < PREFERRED_PERCENT_DOWN*PERCENT_CONVERSION) {
            rate += GOODRATEADD;
        }
        int creditSc = customer.getCreditScore();
        if (creditSc < BADCREDIT) {
            rate += VERYBADRATEADD;
        } else if (creditSc < FAIRCREDIT) {
            rate += BADRATEADD;
        } else if (creditSc < GOODCREDIT) {
            rate += NORMALRATEADD;
        } else if (creditSc < GREATCREDIT) {
            rate += GOODRATEADD;
        } else {
            rate += 0;
        }

        // Payment
        principal = costOfHome - downPayment;
        double monthlyInterestRate = rate/MONTHS_IN_A_YEAR;
        payment = (principal*monthlyInterestRate)/(1-(Math.pow(1+monthlyInterestRate,-(years*MONTHS_IN_A_YEAR))));
    }

    public boolean loanApproved(){
        // Debt To Income Ratio
        // check if loan needs to be included
        double monthlyDebtNoLoan = customer.getMonthlyDebtPayments();
        double monthlyDebtLoan = getPayment();
        double customerIncome = customer.getIncome();
        double debtToIncomeRatio = (((monthlyDebtNoLoan+monthlyDebtLoan)*MONTHS_IN_A_YEAR*getYears())/(customerIncome*getYears()));
        return !(rate >= VERYBADRATEADD || percentDown < MIN_PERCENT_DOWN*PERCENT_CONVERSION || debtToIncomeRatio > DTOITOOHIGH);
    }
    public double getPayment(){ return payment; }

    public double getRate(){ return rate; }

    public double getPrincipal(){ return principal; }

    public int getYears(){ return year; }

    public double getDownPayment(){ return downPayment; }
}
