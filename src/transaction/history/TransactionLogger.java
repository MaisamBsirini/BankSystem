package transaction.history;

import transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionLogger {

    private final List<Transaction> transactions = new ArrayList<>();

    // تسجيل المعاملة
    public void log(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
    }

    // استرجاع كل المعاملات المسجلة (لاختبارات أو عرض)
    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions); // نسخ لتجنب التعديل الخارجي
    }

    // مسح السجل (اختياري)
    public void clear() {
        transactions.clear();
    }
}
