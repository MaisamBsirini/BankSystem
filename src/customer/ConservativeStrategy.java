package customer;
public class ConservativeStrategy implements RecommendationStrategy {

    @Override
    public void recommend(TransactionEvent event) {
        if (event.getType().equals("Deposit")) {
            System.out.println("Conservative Strategy: Save money in fixed deposits.");
        }
    }
}