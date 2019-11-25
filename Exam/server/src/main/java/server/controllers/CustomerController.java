package server.controllers;

import com.google.gson.Gson;
import server.model.Customer;
import server.repository.CustomerRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Contains endpoints for customer related operations.
 */
@Path("/customers")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController()
    {
        this.customerRepository = new CustomerRepository();
    }

    /**
     * Gets a customer by id.
     * @param id The id of the customer.
     * @return The response to the client.
     */

    @GET
    @Path("{id}")
    public Response getCustomer(@PathParam("id") int id){
        Customer customer = customerRepository.getCustomer(id);
        String output = new Gson().toJson(customer);
        System.out.println(output);

        return Response
                .status(200)
                .type("application/json")
                .entity(output)
                .build();
    }

    /**
     * Gets all customers from the database
     * @return Response to the client.
     */
    @GET
    public Response getCustomers(){
        // Get a list of users
        ArrayList<Customer> users = customerRepository.getCustomers();
        String out = new Gson().toJson(users);

        // Return the users with the status code 200
        return Response.status(200).type(MediaType.APPLICATION_JSON).entity(out).build();
    }

    /**
     * Creates a customer from a JSON encoded representation of a customer object.
     * @param customer The JSON encoded representation of a customer object.
     * @return A response indicating if the creation went well.
     */
    @POST
    public Response createCustomer(String customer) {

        Customer customer1 = new Gson().fromJson(customer, Customer.class);

        customerRepository.createCustomer(customer1);

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"userCreated\":\"true\"}")
                .build();
    }

    /**
     * Updates a customer from a JSON String representing a full JSON object.
     * Matches the customer based on the primary key.
     * @param customer A JSON String representing a Customer object.
     * @return A JSON Object with e
     */
    @PUT
    public Response updateCustomer(String customer) {

        Customer customer1 = new Gson().fromJson(customer, Customer.class);
        customerRepository.updateCustomer(customer1);

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"userUpdated\":\"true\"}")
                .build();
    }



}
