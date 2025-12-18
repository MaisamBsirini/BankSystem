package transaction.validator;

import transaction.Transaction;
import account.Account;

public class TransactionValidator {

    public boolean validate(Transaction transaction) {
        if (transaction == null) return false;
        if (transaction.getAmount() <= 0) return false;

        Account from = transaction.getFrom();
        Account to = transaction.getTo();

        if (from == null || to == null) return false;

        // تحقق من أن الحساب المرسل لديه رصيد كافٍ
        if (from.getBalance() < transaction.getAmount()) return false;

        return true; // المعاملة صالحة
    }
}
