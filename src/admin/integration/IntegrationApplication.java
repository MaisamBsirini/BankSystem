package admin.integration;

import admin.integration.adapter.MonitoringServiceAdapter;
import admin.integration.adapter.SystemIntegration;
import admin.integration.external.ExternalMonitoringService;

public class IntegrationApplication {

    public static void main(String[] args) {

        ExternalMonitoringService externalService =
                new ExternalMonitoringService();

        SystemIntegration integration =
                new MonitoringServiceAdapter(externalService);

        System.out.println(integration.getSystemEvent());
    }
}
