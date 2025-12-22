package core;

import account.Account;
import account.SavingsAccount;
import java.util.Scanner;

/**
 * 🏦 BankSystem
 * الكلاس الأساسي لتشغيل النظام البنكي
 */
public class BankSystem {

    public static void main(String[] args) {
        System.out.println("=========== 🏦 ADVANCED BANKING SYSTEM ===========");

        // 🔗 الاتصال بقاعدة البيانات
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.connect();

        // 🧾 مثال إنشاء حساب جديد
        Account acc = new SavingsAccount("A-101", "C-001", 1000, 0.05);
        acc.deposit(500);  // تحديث في قاعدة البيانات

        // 🧰 تسجيل حدث في اللوج
        Logger.log("Created SavingsAccount A-101 for Customer C-001");

        // 💬 اختبار بسيط
        System.out.println("Final Balance: " + acc.getBalance());

        // 🔌 إنهاء الاتصال
        db.disconnect();

        System.out.println("=========== ✅ SYSTEM SHUTDOWN ===========");
    }
}
