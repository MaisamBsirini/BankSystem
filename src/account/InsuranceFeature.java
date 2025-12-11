package account;

import account.Account;

/**
 *  إضافة ميزة التأمين - Insurance Feature
 */
public class InsuranceFeature extends AccountDecorator {

    public InsuranceFeature(Account decoratedAccount) {
        super(decoratedAccount);
    }

    public void applyInsurance() {
        System.out.println(" Insurance coverage active for account: " + decoratedAccount.getAccountId());
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("🛡 Insurance feature active.");
    }
}
