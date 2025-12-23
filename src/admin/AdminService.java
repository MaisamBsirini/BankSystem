package admin;

import admin.reporting.decorator.*;
import customer.support.SupportTicketService;
import transaction.recurring.RecurringTransactionScheduler;

public class AdminService {

    private final RecurringTransactionScheduler scheduler;

    public AdminService() {
        this.scheduler = new RecurringTransactionScheduler();
    }

    // 🧾 إنشاء تقرير إداري
    public void generateAdminReport() {
        Report report = new BaseReport();
        report = new FilteredReport(report);
        report = new EncryptedReport(report);
        report = new SignedReport(report);

        System.out.println("\n=== Admin Generated Report ===");
        System.out.println(report.generate());
        System.out.println("==============================\n");
    }

    // 💬 عرض كل التذاكر
    public void viewTickets(SupportTicketService ticketService) {
        ticketService.listTickets();
    }

    // 🔁 الرد على تذكرة معينة
    public void replyToTicket(SupportTicketService ticketService, int id, String reply) {
        ticketService.replyToTicket(id, reply);
    }

    // ⏹️ إيقاف كل المعاملات المجدولة
    public void stopAllScheduledTransactions() {
        scheduler.stopAll();
    }

    public RecurringTransactionScheduler getScheduler() {
        return scheduler;
    }
}
