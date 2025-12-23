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
        double available = decoratedAccount.getBalance() + overdraftLimit;

        if (amount <= available) {
            double currentBalance = decoratedAccount.getBalance();

            if (amount <= currentBalance) {
                // السحب طبيعي
                decoratedAccount.withdraw(amount);
            } else {
                // سحب من الرصيد الموجود + تغطية الباقي من السحب على المكشوف
                double overdraftUsed = amount - currentBalance;
                // صفر الرصيد الفعلي
                decoratedAccount.balance = 0;
                System.out.println(" Used overdraft of " + overdraftUsed + ". Balance now: 0");
            }

            System.out.println(" Overdraft protection applied.");
        } else {
            System.out.println(" Overdraft limit exceeded.");
        }
    }

}
