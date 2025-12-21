package admin.monitoring.dashboard;

import admin.monitoring.observer.MonitoringObserver;

public class AdminDashboard implements MonitoringObserver {

    @Override
    public void update(String eventData) {
        System.out.println("[DASHBOARD UPDATE] " + eventData);
        refreshView();
    }

    private void refreshView() {
        System.out.println("Dashboard refreshed.\n");
    }
}
