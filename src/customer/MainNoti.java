package customer;



public class MainNoti {
    public static void main(String[] args) {

        NotificationService service = new NotificationService();

        service.attach(new EmailNotification());
        service.attach(new SMSNotification());

        RecommendationEngine engine =
                new RecommendationEngine(new BalancedStrategy());

        service.attach(new Customer("Ahmad", engine));

        TransactionEvent event =
                new TransactionEvent("Deposit", 1500, "ACC-123");

        service.notifyObservers(event);
    }
}
