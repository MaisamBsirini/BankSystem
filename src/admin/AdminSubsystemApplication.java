package admin;

import admin.rbac.model.User;
import admin.rbac.strategy.AdminAccessStrategy;

import admin.monitoring.dashboard.AdminDashboard;
import admin.monitoring.service.TransactionService;

import admin.reporting.decorator.*;

import admin.integration.adapter.MonitoringServiceAdapter;
import admin.integration.adapter.SystemIntegration;
import admin.integration.external.ExternalMonitoringService;

public class AdminSubsystemApplication {

    public static void main(String[] args) {

        System.out.println("=== ADMIN SUBSYSTEM STARTED ===\n");

        /* =======================
           1. RBAC (Strategy)
           ======================= */
        User adminUser = new User("ADMIN-1", new AdminAccessStrategy());

        if (!adminUser.requestAccess("GENERATE_REPORT")) {
            System.out.println("Access denied. Exiting system.");
            return;
        }
        System.out.println("RBAC check passed.\n");

        /* =======================
           2. Monitoring (Observer)
           ======================= */
        TransactionService transactionService = new TransactionService();
        AdminDashboard dashboard = new AdminDashboard();

        transactionService.attach(dashboard);
        transactionService.processTransaction("TX-2001", 1500.00);

        /* =======================
           3. Reporting (Decorator)
           ======================= */
        Report report = new BaseReport();
        report = new FilteredReport(report);
        report = new EncryptedReport(report);

        System.out.println("Generated Report:");
        System.out.println(report.generate() + "\n");

        /* =======================
           4. Integration (Adapter)
           ======================= */
        ExternalMonitoringService externalService =
                new ExternalMonitoringService();

        SystemIntegration integration =
                new MonitoringServiceAdapter(externalService);

        System.out.println("External Integration Event:");
        System.out.println(integration.getSystemEvent());

        System.out.println("\n=== ADMIN SUBSYSTEM FINISHED ===");
    }
}
