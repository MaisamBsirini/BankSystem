package account;

import customer.Customer;

public class LoanAccount extends Account {
    private double loanAmount;
    private double remainingAmount;
    private double interestRate;

    public LoanAccount(String accountId, Customer owner, double loanAmount, double interestRate) {
        super(accountId, owner, 0);
        this.loanAmount = loanAmount;
        this.remainingAmount = loanAmount;
        this.interestRate = interestRate;
        this.state = new ActiveState();
    }

    public void payInstallment(double amount) {
        if (amount > 0 && amount <= remainingAmount) {
            remainingAmount -= amount;
            System.out.println(" Payment of " + amount + " made. Remaining: " + remainingAmount);
        } else {
            System.out.println(" Invalid payment amount.");
        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(" Loan Account: " + accountId + " | Owner: " + owner +
                " | Remaining: " + remainingAmount + " | Interest: " + interestRate);
    }
}
