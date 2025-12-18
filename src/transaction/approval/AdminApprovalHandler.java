package transaction.approval;

import account.Account;
import account.ActiveState;
import transaction.Transaction;
import transaction.TransactionStatus;

public class AdminApprovalHandler extends ApprovalHandler {

    @Override
    public void handle(Transaction tx) {
        Account from = tx.getFrom();
        Account to = tx.getTo();
        double amount = tx.getAmount();

        // Admin approves any amount
        if (from.getBalance() >= amount && from.getState() instanceof ActiveState) {
            from.withdraw(amount);
            to.deposit(amount);
            tx.setStatus(TransactionStatus.APPROVED);
        } else {
            tx.setStatus(TransactionStatus.REJECTED);
        }
    }
}
