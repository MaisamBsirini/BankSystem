package transaction.recurring;

import java.util.concurrent.*;
import java.util.*;

/**
 * Scheduler مسؤول عن جدولة وتنفيذ المعاملات المتكررة.
 * يستخدم ScheduledExecutorService لتشغيلها تلقائيًا.
 */
public class RecurringTransactionScheduler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private final List<ScheduledFuture<?>> scheduledTasks = new ArrayList<>();

    /**
     * جدولة معاملة لتتكرر كل فترة زمنية معينة.
     *
     * @param task الكائن الذي ينفذ المعاملة (RecurringTransaction)
     * @param initialDelay التأخير الأولي بالثواني
     * @param period الفترة الزمنية بين كل تنفيذ (بالثواني)
     */
    public void scheduleRecurring(RecurringTransaction task, long initialDelay, long period) {
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
        scheduledTasks.add(future);
        System.out.println("✅ Transaction scheduled to repeat every " + period + " seconds.");
    }

    /**
     * إيقاف جميع المهام المجدولة
     */
    public void stopAll() {
        for (ScheduledFuture<?> f : scheduledTasks) f.cancel(false);
        scheduler.shutdown();
        System.out.println("🛑 All scheduled transactions stopped.");
    }
}
