package admin.monitoring.observer;

public interface MonitoringSubject {
    void attach(MonitoringObserver observer);
    void detach(MonitoringObserver observer);
    void notifyObservers(String eventData);
}
