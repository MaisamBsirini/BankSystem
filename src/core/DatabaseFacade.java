package core;

import account.Account;
import transaction.Transaction;
import transaction.TransactionType;
import transaction.TransactionStatus;

import java.sql.*;

public class DatabaseFacade {

    private Connection connection;

    public DatabaseFacade(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ----------------- Accounts -----------------

    public void insertAccount(Account account) {
        String sql = "INSERT INTO accounts(account_id, owner_id, balance, state) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountId());
            stmt.setString(2, account.getOwnerId());
            stmt.setDouble(3, account.getBalance());
            stmt.setString(4, account.getState() != null ? account.getState().toString() : "ACTIVE");
            stmt.executeUpdate();
            System.out.println("Account " + account.getAccountId() + " inserted into database.");
        } catch (SQLException e) {
            System.out.println("Error inserting account: " + e.getMessage());
        }
    }

    public void updateAccountBalance(Account account) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getAccountId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating account balance: " + e.getMessage());
        }
    }

    public void updateAccountState(Account account) {
        String sql = "UPDATE accounts SET state = ? WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getState().toString());
            stmt.setString(2, account.getAccountId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating account state: " + e.getMessage());
        }
    }

    // ----------------- Transactions -----------------

    public void insertTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions(transaction_id, from_account_id, to_account_id, amount, type, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transaction.getId());
            stmt.setString(2, transaction.getFrom() != null ? transaction.getFrom().getAccountId() : null);
            stmt.setString(3, transaction.getTo() != null ? transaction.getTo().getAccountId() : null);
            stmt.setDouble(4, transaction.getAmount());
            stmt.setString(5, transaction.getType().toString());
            stmt.setString(6, transaction.getStatus().toString());
            stmt.executeUpdate();
            System.out.println("Transaction " + transaction.getId() + " recorded in database.");
        } catch (SQLException e) {
            System.out.println("Error inserting transaction: " + e.getMessage());
        }
    }

}
