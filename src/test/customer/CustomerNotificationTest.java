package test.customer;

import customer.*;
import org.junit.jupiter.api.Test;

// اختبارات إشعارات العملاء
public class CustomerNotificationTest {

    // استراتيجية بسيطة للاختبار
    static class DummyRecommendationStrategy implements RecommendationStrategy {
        @Override
        public void recommend(TransactionEvent event) {
            System.out.println("💡 Dummy recommendation for event: "
                    + event.getType() + " | Amount: $" + event.getAmount());
        }
    }

    @Test
    void testUpdateNotification() {
        // إنشاء محرك التوصيات مع استراتيجية Dummy
        RecommendationEngine engine = new RecommendationEngine(new DummyRecommendationStrategy());

        // إنشاء عميل مع محرك التوصيات
        Customer customer = new Customer("Ahmad", engine);

        // إنشاء حدث تجريبي للمعاملة
        TransactionEvent depositEvent = new TransactionEvent("Deposit", 500, "A1");

        // تحديث العميل بالحدث (سيطبع الإشعار والتوصية)
        customer.update(depositEvent);

        // حدث آخر للانسحاب
        TransactionEvent withdrawEvent = new TransactionEvent("Withdrawal", 200, "A1");
        customer.update(withdrawEvent);
    }
}
