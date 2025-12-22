package core;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 🧾 Logger - يسجل كل الأحداث في النظام
 */
public class Logger {
    private static final String LOG_FILE = "logs/system_log.txt";

    public static void log(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            String log = LocalDateTime.now() + " - " + message + "\n";
            writer.write(log);
            System.out.println("🪵 LOG: " + message);
        } catch (IOException e) {
            System.out.println("⚠️ Logger failed: " + e.getMessage());
        }
    }
}
