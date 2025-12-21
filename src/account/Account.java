package account;

/**
 *  الكلاس الأساسي لكل الحسابات البنكية.
 * يحتوي على الخصائص العامة (رقم الحساب، صاحب الحساب، الرصيد، الحالة)
 * ويتضمن عمليات الإيداع، السحب، والتحويل.
 * يتم توسيعه من أنواع الحسابات الأخرى (Savings, Checking...).
 */
public abstract class Account {

    protected String accountId;
    protected String ownerId;
    protected double balance;
    protected AccountState state;

    public Account(String accountId, String ownerId, double initialBalance) {
        this.accountId = accountId;
        this.ownerId = ownerId;
        this.balance = initialBalance;
    }

    //  دالة لإيداع مبلغ
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Deposit of " + amount + " completed. New balance: " + balance);

            //  ملاحظة: هون ممكن نربط مع NotificationService من قسم العملاء لإرسال إشعار.
            // TODO: Notify customer via NotificationService (Customer subsystem)
        }
    }

    //  دالة لسحب مبلغ
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(" Withdrawal of " + amount + " completed. New balance: " + balance);
        } else {
            System.out.println(" Insufficient balance or invalid amount.");
        }
    }

    //  دالة لتحويل الأموال بين الحسابات
    public void transfer(Account target, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            target.deposit(amount);
            System.out.println(" Transfer of " + amount + " from " + accountId + " to " + target.accountId + " successful.");

            //  ملاحظة: هون ممكن نربط مع Transaction System لتسجيل العملية في سجل المعاملات.
            // TODO: Log this transaction in Transaction subsystem
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
