package server.repository;

import server.model.Account;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A repository class for database operations for account objects.
 */
public class AccountRepository extends BaseRepository {

    public ArrayList<Account> getAccountsForCustomer (int customerId) {
        String sql = "SELECT * FROM accounts WHERE customer_id = ?";

        PreparedStatement ps = super.prepareQuery(sql);

        try {
            ps.setInt(1, customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = super.executePreparedStatementQuery(ps);


        ArrayList<Account> accounts = new ArrayList<>();
        if (rs == null)
            return accounts;

        try {
            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("account_id"),
                        rs.getInt("balance"),
                        rs.getInt("costumer_id"));
                accounts.add(account);
            }
        } catch (SQLException ex) {
            //TODO: Add some sensible error handling!
        }
        return accounts;
    }




    /**
     * Creates a account in the database.
     * @param account The Account to save to the database.
     * @return The account with the primary key added.
     */
    public Account createAccount(Account account) {

        String sql = "INSERT INTO accounts (customer_id, balance) VALUES (?, ?)";

        try{
            PreparedStatement ps = prepareInsert(sql);
            ps.setInt(1, account.getCustomer_id());
            ps.setInt(2, account.getBalance());
            int primary_key = executeInsertPreparedStatement(ps);
            account.setAccount_id(primary_key);

        } catch (SQLException ex) {
            ex.printStackTrace();
            //TODO: Add some sensible error handling!
            //For example you may use
            // throw ex;
        }
        // Return the new account
        return account;
    }




    /**
     * Gets all accounts from the database.
     * @return A list of accounts.
     */
    public ArrayList<Account> getAccounts() {

        String sql = "SELECT * FROM accounts";

        ResultSet rs = executePreparedStatementQuery(prepareQuery(sql));

        ArrayList<Account> accounts = new ArrayList<>();
        if (rs == null)
            return accounts;

        try {
            while (rs.next()) {
                Account account =
                        new Account(
                                rs.getInt("account_id"),
                                rs.getInt("balance"),
                                rs.getInt("costumer_id"));
                accounts.add(account);
            }
        } catch (SQLException ex) {
            //TODO: Add some sensible error handling!
        }

        return accounts;
    }




    public void withdraw(Account account2) {

        String sql = "UPDATE account SET balance = ? WHERE account_id = ?";
        PreparedStatement ps =  prepareQuery(sql);

        try {
            ps.setInt(1, account2.getBalance());
            ps.setInt(2 , account2.getAccount_id());
            executePreparedStatementQuery(ps);

        } catch (SQLException ex) {
            //TODO: Add some sensible error handling! What happens if the primary key is not set?
        }

    }



}
