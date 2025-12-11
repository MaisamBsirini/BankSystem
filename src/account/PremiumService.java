package account;

import account.Account;

/**
 *  ميزة الخدمات المميزة - Premium Service
 */
public class PremiumService extends AccountDecorator {

    public PremiumService(Account decoratedAccount) {
        super(decoratedAccount);
    }

    public void offerPrioritySupport() {
        System.out.println(" Premium Support activated for " + decoratedAccount.getAccountId());
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println(" Premium Service enabled.");
    }
}
