package core;

/**
 * 🚨 ErrorHandler - معالجة الأخطاء وتسجيلها
 */
public class ErrorHandler {
    public static void handle(Exception e) {
        System.out.println("❌ ERROR: " + e.getMessage());
        Logger.log("ERROR: " + e.toString());
    }
}
