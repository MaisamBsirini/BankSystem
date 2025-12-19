package customer;


import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Subject {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(TransactionEvent event) {
        for (Observer o : observers) {
            o.update(event);
        }
    }
}
