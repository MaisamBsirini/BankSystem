package core;

import account.Account;
import account.SavingsAccount;
import customer.Customer;
import transaction.Transaction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 💾 DatabaseFacade
 * واجهة بسيطة للتعامل مع DB لكل الأقسام
 */
public class DatabaseFacade {

    private DatabaseConnection db;

    public DatabaseFacade() {
        db = DatabaseConnection.getInstance();
    }

    // ------------------ Customers ------------------
    public void addCustomer(Customer customer) {
        try {
            String sql = "INSERT INTO customers (customer_id, full_name, email, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, customer.getCustomerId());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.executeUpdate();
            core.Logger.log("Customer " + customer.getCustomerId() + " added.");
        } catch (SQLException e) {
            core.ErrorHandler.handle(e);
        }
    }

    public Customer getCustomer(String customerId) {
        try {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getString("customer_id"), rs.getString("full_name"),
                        rs.getString("email"), rs.getString("phone"));
            }
        } catch (SQLException e) {
            core.ErrorHandler.handle(e);
        }
        return null;
    }

    // ------------------ Accounts ------------------
    public void addAccount(Account account) {
        try {
            String sql = "INSERT INTO accounts (account_id, owner_id, account_type, balance, interest_rate, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, account.getAccountId());
            stmt.setString(2, account.getOwnerId());
            stmt.setString(3, account.getAccountType());
            stmt.setDouble(4, account.getBalance());
            stmt.setDouble(5, account.getInterestRate());
            stmt.setString(6, account.getStatus());
            stmt.executeUpdate();
            core.Logger.log("Account " + account.getAccountId() + " added.");
        } catch (SQLException e) {
            core.ErrorHandler.handle(e);
        }
    }

    public void updateAccountBalance(Account account) {
        try {
            String sql = "UPDATE accounts SET balance = ?, status = ? WHERE account_id = ?";
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getStatus());
            stmt.setString(3, account.getAccountId());
            stmt.executeUpdate();
            core.Logger.log("Account " + account.getAccountId() + " balance updated.");
        } catch (SQLException e) {
            core.ErrorHandler.handle(e);
        }
    }

    // ------------------ Transactions ------------------
    public void addTransaction(Transaction transaction) {
        try {
            String sql = "INSERT INTO transactions (account_id, type, amount) VALUES (?, ?, ?)";
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, transaction.getAccountId());
            stmt.setString(2, transaction.getType());
            stmt.setDouble(3, transaction.getAmount());
            stmt.executeUpdate();
            core.Logger.log("Transaction for account " + transaction.getAccountId() + " recorded.");
        } catch (SQLException e) {
            core.ErrorHandler.handle(e);
        }
    }

}
