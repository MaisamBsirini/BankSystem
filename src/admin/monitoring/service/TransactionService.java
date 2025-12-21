package admin.monitoring.service;

import admin.monitoring.observer.MonitoringObserver;
import admin.monitoring.observer.MonitoringSubject;

import java.util.ArrayList;
import java.util.List;

public class TransactionService implements MonitoringSubject {

    private final List<MonitoringObserver> observers = new ArrayList<>();

    @Override
    public void attach(MonitoringObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(MonitoringObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String eventData) {
        for (MonitoringObserver observer : observers) {
            observer.update(eventData);
        }
    }

    public void processTransaction(String transactionId, double amount) {
        String event = "Transaction processed: ID=" + transactionId + ", Amount=" + amount;
        notifyObservers(event);
    }
}
