package com.example.customers.customer;

import com.example.customers.address.Address;

import java.util.Set;

public interface CustomerService {
    Customer addCustomer(Customer toAdd);

    Set<Customer> getAllCustomers();

    Customer getCustomerById(long customerId);
}
