package account;

import customer.Customer;
import customer.TransactionEvent;

/**
 *  الكلاس الأساسي لكل الحسابات البنكية.
 * يحتوي على الخصائص العامة (رقم الحساب، صاحب الحساب، الرصيد، الحالة)
 * ويتضمن عمليات الإيداع، السحب، والتحويل.
 * يتم توسيعه من أنواع الحسابات الأخرى (Savings, Checking...).
 */
public abstract class Account {

    protected String accountId;
    protected Customer owner;
    protected String ownerId;
    protected double balance;
    protected AccountState state;

    public Account(String accountId, Customer owner, double initialBalance) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = initialBalance;
    }

    public Customer getOwner() {
        return owner;
    }

    public void deposit(double amount) {
        if (!(state instanceof ActiveState)) {
            System.out.println("Cannot deposit: Account inactive");
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        balance += amount;
        System.out.println("Deposit of " + amount + " completed. New balance: " + balance);

        // إشعار العميل
        if (owner != null) {
            TransactionEvent event = new TransactionEvent("Deposit", amount, accountId);
            owner.update(event);  // هذا يستدعي NotificationService و RecommendationEngine
        }
    }

    public void withdraw(double amount) {
        if (!(state instanceof ActiveState)) {
            System.out.println("Cannot withdraw: Account inactive");
            return;
        }

        if (amount <= 0 || balance < amount) {
            System.out.println("Insufficient balance or invalid amount.");
            return;
        }

        balance -= amount;
        System.out.println("Withdrawal of " + amount + " completed. New balance: " + balance);

        // إشعار العميل
        if (owner != null) {
            TransactionEvent event = new TransactionEvent("Withdrawal", amount, accountId);
            owner.update(event);
        }
    }

    public void transfer(Account target, double amount) {
        if (!(state instanceof ActiveState)) {
            System.out.println("Cannot transfer: Account inactive");
            return;
        }

        if (amount <= 0 || balance < amount) {
            System.out.println("Insufficient balance or invalid amount.");
            return;
        }

        // سحب من الحساب المرسل
        this.withdraw(amount);

        // إيداع في الحساب المستلم
        target.deposit(amount);

        System.out.println("Transfer of " + amount + " from " + accountId + " to " + target.getAccountId() + " successful.");

        // إشعار صاحب الحساب المستلم
        if (target.getOwner() != null) {
            TransactionEvent event = new TransactionEvent("Deposit", amount, target.getAccountId());
            target.getOwner().update(event);
        }
    }



    //  تغيير حالة الحساب (Active, Frozen, etc.)
    public void setState(AccountState state) {
        this.state = state;
    }

    public AccountState getState() {
        return state;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    //  دالة لعرض معلومات الحساب
    public abstract void displayAccountInfo();
}
