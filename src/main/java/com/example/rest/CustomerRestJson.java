package com.example.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@Path("customerjson")
public class CustomerRestJson {

    /**
     * Class for holding the list of customers and handling the requests
     */

    private static ArrayList<Customer> customers = new ArrayList<>();

    /**
     * Meant for returning the list of customers
     * @return A concatenation of the toString method for all customers
     */
    @GET
    @Produces("application/json")
    public ArrayList<Customer> getCustomer() {
        return customers;
    }

    /**
     * Meant for getting a customer with a specific ID
     * @param id of the customer
     * @return toString method of customer
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Customer getCustomerList(@PathParam("id") int id) {
        Customer customer = customers.stream().filter(customer1 -> customer1.getId() == id)
                .findFirst()
                .orElse(null);
        return customer;
    }

    /**
     * Meant for creating customers using the post method
     * @param customer to create
     */
    @POST
    @Consumes("application/json")
    public void createCustomer(Customer customer) {
        Customer newCustomer = new Customer(customer);
        customers.add(newCustomer);
    }

    /**
     * Meant for replacing customer with specific ID
     * @param id of the customer
     * @param customer to replace with
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void modifyCustomer(@PathParam("id") int id, Customer customer) {
        deleteCustomer(id);
        customers.add(new Customer(customer));
    }

    /**
     * Meant for deleting customer with specific ID
     * @param id of the customer
     */
    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        customers = customers.stream().filter(customer -> customer.getId() != id)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}