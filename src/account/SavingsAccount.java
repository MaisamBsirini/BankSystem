package account;

import customer.Customer;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountId, Customer owner, double balance, double interestRate) {
        super(accountId, owner, balance);
        this.interestRate = interestRate;
        this.state = new ActiveState();
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println(" Interest added: " + interest + ". New balance: " + balance);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(" Savings Account: " + accountId +
                " | Owner: " + owner +
                " | Balance: " + balance +
                " | Interest: " + interestRate);
    }
}
