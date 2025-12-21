package customer;

public interface RecommendationStrategy {
    void recommend(TransactionEvent event);
}
