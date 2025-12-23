package test.performance;

import account.*;
import customer.Customer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class PerformanceTest {

    @Test
    void testConcurrentTransactions() throws InterruptedException {
        int userCount = 100; // عدد المستخدمين
        ExecutorService executor = Executors.newFixedThreadPool(userCount);

        // إنشاء حساب مشترك
        Customer customer = new Customer("TestUser", null);
        Account account = new CheckingAccount("A-100", customer, 10000, 5);

        // نستخدم عدّاد للعمليات
        CountDownLatch latch = new CountDownLatch(userCount);

        long start = System.currentTimeMillis();

        for (int i = 0; i < userCount; i++) {
            int id = i;
            executor.submit(() -> {
                try {
                    // كل مستخدم يعمل عمليات عشوائية
                    if (id % 2 == 0) {
                        account.deposit(100);
                    } else {
                        account.withdraw(50);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // ننتظر انتهاء الكل
        executor.shutdown();

        long duration = System.currentTimeMillis() - start;
        System.out.println("✅ Completed " + userCount + " operations in " + duration + " ms");
        System.out.println("Final balance: " + account.getBalance());
    }
}
