package customer;

public class Customer implements Observer {

    private String name;
    private RecommendationEngine engine;

    public Customer(String name, RecommendationEngine engine) {
        this.name = name;
        if (engine == null) {
            this.engine = new RecommendationEngine(new DefaultRecommendationStrategy());
        } else {
            this.engine = engine;
        }
    }

    @Override
    public void update(TransactionEvent event) {
        System.out.println(
                "Customer " + name +
                        " notified about " + event.getType() +
                        " of $" + event.getAmount()
        );
        engine.process(event);
    }
    public String getName() {
        return name;
    }

}
