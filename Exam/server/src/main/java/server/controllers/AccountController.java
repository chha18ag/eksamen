package server.controllers;

import com.google.gson.Gson;
import server.model.Account;
import server.model.Customer;
import server.repository.AccountRepository;
import server.repository.CustomerRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Contains the endpoints for account operations.
 */


@Path("/accounts")
public class AccountController {

    private AccountRepository accountRepository;

    public AccountController () { this.accountRepository = new AccountRepository();}


    //Todo: Add the needed account endpoints here.
    @GET
    @Path("{id}")
    public Response getAccounts(@PathParam("id") int id) {
        // Get a list of accounts
        ArrayList<Account> accounts = accountRepository.getAccounts();
        String out = new Gson().toJson(accounts);

        // Return the accounts with the status code 200
        return Response.status(200).type(MediaType.APPLICATION_JSON).entity(out).build();
    }

    @GET
    @Path("{id}")
    public Response getAccountsForCustomer (@PathParam("id") int id){
        ArrayList<Account> accounts = accountRepository.getAccountsForCustomer(id);
        String output = new Gson().toJson(accounts);
        System.out.println(output);

        return Response
                .status(200)
                .type("application/json")
                .entity(output)
                .build();
    }

    @POST
        public Response createAccount(String account) {

            Account account1 = new Gson().fromJson(account, Account.class);

            accountRepository.createAccount(account1);

            return Response
                    .status(200)
                    .type("application/json")
                    .entity("{\"userCreated\":\"true\"}")
                    .build();
        }

    @PUT
    public Response withdraw(String account) {

        Account account2 = new Gson().fromJson(account, Account.class);
        accountRepository.withdraw(account2);

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"accountUpdated\":\"true\"}")
                .build();
    }
    }