package transaction.approval;

import account.Account;
import transaction.Transaction;
import transaction.TransactionStatus;

public class AutoApprovalHandler extends ApprovalHandler {

    @Override
    public void handle(Transaction tx) {
        Account from = tx.getFrom();
        Account to = tx.getTo();
        double amount = tx.getAmount();

        if (tx.getAmount() < 1000) { // معاملات صغيرة
            if (from.getBalance() >= amount) {  // بدل if (withdraw)
                from.withdraw(amount);
                to.deposit(amount);
                tx.setStatus(TransactionStatus.APPROVED);
            } else {
                tx.setStatus(TransactionStatus.REJECTED);
            }
        } else if (nextHandler != null) {
            nextHandler.handle(tx);
        }
    }
}
