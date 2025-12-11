package account;

import account.Account;

/**
 * إضافة ميزة السحب على المكشوف - Overdraft Protection
 */
public class OverdraftProtection extends AccountDecorator {
    private double overdraftLimit = 500.0;

    public OverdraftProtection(Account decoratedAccount) {
        super(decoratedAccount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= decoratedAccount.getBalance() + overdraftLimit) {
            decoratedAccount.withdraw(amount);
            System.out.println(" Overdraft protection applied.");
        } else {
            System.out.println(" Overdraft limit exceeded.");
        }
    }
}
