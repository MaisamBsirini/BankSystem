package account;

import customer.Customer;

public class InvestmentAccount extends Account {
    private double investmentAmount;
    private double returnRate;

    public InvestmentAccount(String accountId, Customer owner, double investmentAmount, double returnRate) {
        super(accountId, owner, investmentAmount);
        this.investmentAmount = investmentAmount;
        this.returnRate = returnRate;
        this.state = new ActiveState();
    }

    public void calculateReturns() {
        double profit = investmentAmount * returnRate;
        balance += profit;
        System.out.println(" Profit added: " + profit + ". New balance: " + balance);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(" Investment Account: " + accountId + " | Owner: " + owner +
                " | Balance: " + balance + " | Return rate: " + returnRate);
    }
}
