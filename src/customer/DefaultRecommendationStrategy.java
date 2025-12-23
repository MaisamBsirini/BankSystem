package customer;

public class DefaultRecommendationStrategy implements RecommendationStrategy {

    @Override
    public void recommend(TransactionEvent event) {
        System.out.println("Default recommendation executed for " + event.getType());
    }
}
