package server.repository;

import server.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A repository class for database operations for customer objects.
 */
public class CustomerRepository extends BaseRepository {

    /**
     * Creates a customer in the database.
     * @param customer The customer to save to the database.
     * @return The customer with the primary key added.
     */
    public Customer createCustomer(Customer customer) {

        String sql = "INSERT INTO customers (name) VALUES (?)";

        try {
            PreparedStatement ps = prepareInsert(sql);
            ps.setString(1, customer.getName());
            int primary_key = executeInsertPreparedStatement(ps);
            customer.setCustomerId(primary_key);

        } catch (SQLException ex) {
            //TODO: Add some sensible error handling!
            //For example you may use
            // throw ex;
        }
        // Return the new customer
        return customer;
    }

    /**
     * Updates a customer in the database based on the primary key.
     * @param customer The customer to update.
     */
    public void updateCustomer(Customer customer) {

        String sql = "UPDATE customers SET name = ? WHERE customer_id = ?";
        PreparedStatement ps =  prepareQuery(sql);

        try {
            ps.setString(1, customer.getName());
            ps.setInt(2 , customer.getCustomerId());
            executePreparedStatementQuery(ps);

        } catch (SQLException ex) {
            //TODO: Add some sensible error handling! What happens if the primary key is not set?
        }
    }

    /**
     * Gets all customers from the database.
     * @return A list of customers.
     */
    public ArrayList<Customer> getCustomers() {

        String sql = "SELECT * FROM customers";

        ResultSet rs = executePreparedStatementQuery(prepareQuery(sql));

        ArrayList<Customer> customers = new ArrayList<>();
        if (rs == null)
            return customers;

        try {
            while (rs.next()) {
                Customer customer =
                        new Customer(
                                rs.getString("name"),
                                rs.getInt("customer_id"));
                customers.add(customer);
            }
        } catch (SQLException ex) {
            //TODO: Add some sensible error handling!
        }

        return customers;
    }

    /**
     * Gets a customer from the database by id.
     * @param customer_id The id of the customer.
     * @return The customer, if found. Else null.
     */
    public Customer getCustomer(int customer_id)
    {
        Customer customer = null;
        try {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement ps = super.prepareQuery(sql);

            ps.setInt(1, customer_id);
            ResultSet rs = super.executePreparedStatementQuery(ps);

            if (rs != null && rs.next())
                customer = new Customer(rs.getString("name"), rs.getInt("customer_id"));

        } catch (Exception e) {
            //TODO: Add some sensible error handling!
        }

        return customer;
    }
}
