package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 🗄️ DatabaseConnection - Singleton Pattern
 * مسؤول عن إدارة الاتصال بقاعدة بيانات MySQL
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/advanced_banking_system";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // افتراضي في XAMPP

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MySQL database successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            ErrorHandler.handle(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Database disconnected.");
            }
        } catch (SQLException e) {
            ErrorHandler.handle(e);
        }
    }
}
