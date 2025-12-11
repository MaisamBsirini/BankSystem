package test;

public class AccountTests {
    public static void main(String[] args) {

        System.out.println("===========  ACCOUNT MANAGEMENT TESTS START ===========");

        // ⃣ اختبار إنشاء حساب توفير
        System.out.println("\n--- [Savings Account Test] ---");
        Account savings = new SavingsAccount("S-1001", "C-001", 1000.0, 0.05);
        savings.deposit(500);
        savings.withdraw(200);
        ((SavingsAccount) savings).applyInterest();
        savings.displayAccountInfo();

        // ⃣ اختبار الحساب الجاري
        System.out.println("\n--- [Checking Account Test] ---");
        Account checking = new CheckingAccount("C-2001", "C-001", 800.0, 2.5);
        checking.withdraw(100);
        checking.displayAccountInfo();

        //  اختبار حساب القرض
        System.out.println("\n--- [Loan Account Test] ---");
        LoanAccount loan = new LoanAccount("L-3001", "C-001", 5000.0, 0.1);
        loan.payInstallment(1000);
        loan.displayAccountInfo();

        //  اختبار حساب الاستثمار
        System.out.println("\n--- [Investment Account Test] ---");
        InvestmentAccount inv = new InvestmentAccount("I-4001", "C-001", 2000.0, 0.15);
        inv.calculateReturns();
        inv.displayAccountInfo();

        //  اختبار الحساب المركب (Composite)
        System.out.println("\n--- [Composite Account Test] ---");
        CompositeAccount parentAcc = new CompositeAccount("F-5000", "Family-01");
        parentAcc.addAccount(savings);
        parentAcc.addAccount(checking);
        parentAcc.displayAccountInfo();

        //  اختبار حالات الحساب (State Pattern)
        System.out.println("\n--- [Account State Test] ---");
        savings.setState(new ActiveState());
        savings.state.freeze(savings);
        savings.state.activate(savings);
        savings.state.close(savings);
        savings.state.freeze(savings);

        //  اختبار الميزات الإضافية (Decorator Pattern)
        System.out.println("\n--- [Account Decorator Test] ---");
        Account decoratedAccount = new PremiumService(
                new InsuranceFeature(
                        new OverdraftProtection(checking)
                )
        );
        decoratedAccount.displayAccountInfo();
        decoratedAccount.withdraw(1200); // سحب فوق الرصيد (Overdraft)

        System.out.println("\n===========  ALL ACCOUNT TESTS COMPLETED SUCCESSFULLY ===========");
    }
}
