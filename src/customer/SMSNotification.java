package customer;

public class SMSNotification implements Observer {

    @Override
    public void update(TransactionEvent event) {
        System.out.println(
                "SMS Notification -> " +
                        event.getType() +
                        ": $" + event.getAmount()
        );
    }
}
