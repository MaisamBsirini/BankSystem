package customer;

public class EmailNotification implements Observer {

    @Override
    public void update(TransactionEvent event) {
        System.out.println(
                "Email Notification -> " +
                        event.getType() +
                        " of $" + event.getAmount() +
                        " for account " + event.getAccountNumber()
        );
    }
}
