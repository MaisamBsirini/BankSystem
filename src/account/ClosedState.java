package account;

import account.Account;

/**
 *  الحساب المغلق - لا يسمح بأي عملية.
 */
public class ClosedState implements AccountState {
    @Override
    public void activate(Account account) {
        System.out.println(" Cannot activate a closed account.");
    }

    @Override
    public void freeze(Account account) {
        System.out.println(" Cannot freeze a closed account.");
    }

    @Override
    public void suspend(Account account) {
        System.out.println(" Cannot suspend a closed account.");
    }

    @Override
    public void close(Account account) {
        System.out.println(" Account already closed.");
    }
}
