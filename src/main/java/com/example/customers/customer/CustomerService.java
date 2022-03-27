package com.example.customers.customer;

import java.util.Optional;
import java.util.Set;

public interface CustomerService {
    Customer addCustomer(Customer toAdd);

    Set<Customer> getAllCustomers();

    Customer getCustomerById(long customerId);

    Optional<Customer> getCustomerByName(String customerName);
}
