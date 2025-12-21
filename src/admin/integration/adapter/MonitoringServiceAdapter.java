package admin.integration.adapter;

import admin.integration.external.ExternalMonitoringService;

public class MonitoringServiceAdapter implements SystemIntegration {

    private final ExternalMonitoringService externalService;

    public MonitoringServiceAdapter(ExternalMonitoringService externalService) {
        this.externalService = externalService;
    }

    @Override
    public String getSystemEvent() {
        // Translate external interface to internal interface
        return externalService.fetchEventData();
    }
}
