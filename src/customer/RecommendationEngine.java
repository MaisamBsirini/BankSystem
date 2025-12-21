package customer;





public class RecommendationEngine {

    private RecommendationStrategy strategy;

    public RecommendationEngine(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public void process(TransactionEvent event) {
        strategy.recommend(event);
    }
}
