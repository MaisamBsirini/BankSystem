package customer;



public class AggressiveStrategy implements RecommendationStrategy {

    @Override
    public void recommend(TransactionEvent event) {
        if (event.getType().equals("Deposit") && event.getAmount() > 1000) {
            System.out.println("Aggressive Strategy: Invest in high-risk assets!");
        }
    }
}


