package account;

import account.Account;

/**
 *  Base Decorator Class - Decorator Pattern
 * يسمح بإضافة ميزات جديدة على الحساب بدون تعديل كوده الأساسي.
 */
public abstract class AccountDecorator extends Account {
    protected Account decoratedAccount;

    public AccountDecorator(Account decoratedAccount) {
        super(decoratedAccount.getAccountId(), decoratedAccount.getOwner(), decoratedAccount.getBalance());
        this.decoratedAccount = decoratedAccount;
    }

    @Override
    public void displayAccountInfo() {
        decoratedAccount.displayAccountInfo();
    }
}
