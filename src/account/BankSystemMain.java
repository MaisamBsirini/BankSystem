package account;

import java.util.Scanner;

/**
 *  كلاس تفاعلي بسيط لتجربة قسم الحسابات
 * يتيح للمستخدم إدخال بيانات الحساب وتجربة الإيداع والسحب والتحويل.
 */
public class BankSystemMain {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("===========  WELCOME TO BANK SYSTEM TEST ===========");

        //  إنشاء حساب توفير بسيط بناءً على إدخال المستخدم
        System.out.print("Enter Account ID: ");
        String id = input.nextLine();

        System.out.print("Enter Owner ID: ");
        String owner = input.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = input.nextDouble();

        System.out.print("Enter Interest Rate (e.g., 0.05 for 5%): ");
        double rate = input.nextDouble();

        Account acc = new SavingsAccount(id, owner, balance, rate);
        acc.displayAccountInfo();

        //  قائمة خيارات
        int choice;
        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Apply Interest");
            System.out.println("4. Show Info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double dep = input.nextDouble();
                    acc.deposit(dep);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double w = input.nextDouble();
                    acc.withdraw(w);
                    break;

                case 3:
                    ((SavingsAccount) acc).applyInterest();
                    break;

                case 4:
                    acc.displayAccountInfo();
                    break;

                case 5:
                    System.out.println(" Exiting system...");
                    break;

                default:
                    System.out.println(" Invalid choice. Try again.");
            }

        } while (choice != 5);

        input.close();
        System.out.println("===========  SESSION ENDED SUCCESSFULLY ===========");
    }
}
