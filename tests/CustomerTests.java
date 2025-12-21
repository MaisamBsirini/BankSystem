import org.example.customer.Customer;
import org.example.event.TransactionEvent;
import org.example.notification.NotificationService;
import org.example.observer.Observer;
import org.example.strategy.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/*
 * CustomerTestes
 * This class contains unit tests for:
 * - Strategy Pattern
 * - Observer Pattern
 * - Customer behavior
 */

public class CustomerTestes {

    // =====================================================
    // Strategy Pattern Tests
    // =====================================================

    // Test Aggressive Strategy with large deposit
    @Test
    void shouldRecommendForLargeDeposit_AggressiveStrategy() {
        AggressiveStrategy strategy = new AggressiveStrategy();

        TransactionEvent event =
                new TransactionEvent("Deposit", 2000, "ACC-1");

        strategy.recommend(event);
        // Test passes if no exception occurs
    }

    // Test Balanced Strategy with medium deposit
    @Test
    void shouldRecommendForMediumDeposit_BalancedStrategy() {
        BalancedStrategy strategy = new BalancedStrategy();

        TransactionEvent event =
                new TransactionEvent("Deposit", 500, "ACC-2");

        strategy.recommend(event);
    }

    // Test Conservative Strategy with any deposit
    @Test
    void shouldRecommendForAnyDeposit_ConservativeStrategy() {
        ConservativeStrategy strategy = new ConservativeStrategy();

        TransactionEvent event =
                new TransactionEvent("Deposit", 300, "ACC-3");

        strategy.recommend(event);
    }

    // =====================================================
    // Recommendation Engine Test
    // =====================================================

    // Test that RecommendationEngine delegates event to strategy
    @Test
    void shouldDelegateEventToStrategy() {
        RecommendationStrategy strategy =
                Mockito.mock(RecommendationStrategy.class);

        RecommendationEngine engine =
                new RecommendationEngine(strategy);

        TransactionEvent event =
                new TransactionEvent("Deposit", 700, "ACC-4");

        engine.process(event);

        Mockito.verify(strategy).recommend(event);
    }

    // =====================================================
    // Customer Observer Test
    // =====================================================

    // Test that customer calls recommendation engine when notified
    @Test
    void customerShouldCallEngineOnUpdate() {
        RecommendationEngine engine =
                Mockito.mock(RecommendationEngine.class);

        Customer customer = new Customer("Ali", engine);

        TransactionEvent event =
                new TransactionEvent("Deposit", 1000, "ACC-5");

        customer.update(event);

        Mockito.verify(engine).process(event);
    }

    // =====================================================
    // Notification Service (Observer Pattern) Test
    // =====================================================

    // Test that all attached observers are notified
    @Test
    void shouldNotifyAllObservers() {
        NotificationService service = new NotificationService();

        Observer observer1 = Mockito.mock(Observer.class);
        Observer observer2 = Mockito.mock(Observer.class);

        service.attach(observer1);
        service.attach(observer2);

        TransactionEvent event =
                new TransactionEvent("Deposit", 400, "ACC-6");

        service.notifyObservers(event);

        Mockito.verify(observer1).update(event);
        Mockito.verify(observer2).update(event);
    }
}
