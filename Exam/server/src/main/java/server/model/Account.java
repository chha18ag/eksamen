package server.model;

/**
 * A class representing an account in the system.
 *
 */
public class Account {
    //TODO: Implement the class. See init.sql for suggested fields - though remember that they may be
    //named differently here, if you so wish.

    private int account_id;
    private int balance;
    private int customer_id;

    public Account(int account_id, int balance, int customer_id) {
        this.account_id = account_id;
        this.balance = balance;
        this.customer_id = customer_id;
    }

    public Account(){}

    public int getAccount_id() { return account_id; }

    public void setAccount_id(int account_id) { this.account_id = account_id; }

    public int getBalance() { return balance; }

    public void setBalance(int balance) { this.balance = balance; }

    public int getCustomer_id() { return customer_id; }

    public void setCustomer_id(int customer_id) { this.customer_id = customer_id; }
}
