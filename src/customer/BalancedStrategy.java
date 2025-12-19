package customer;

public class BalancedStrategy implements RecommendationStrategy {

    @Override
    public void recommend(TransactionEvent event) {
        if (event.getType().equals("Deposit") && event.getAmount() >= 500) {
            System.out.println("Balanced Strategy: Invest in mixed portfolio.");
        }
    }
}
