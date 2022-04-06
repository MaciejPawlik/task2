package com.example.customers.customer;

import java.util.Optional;
import java.util.Set;

public interface CustomerService {
    Customer addCustomer(Customer toAdd);

    Set<Customer> getAllCustomers();

    Customer getCustomerById(long customerId);

    boolean existsCustomerByName(String customerName);
}
