package com.example.customers.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Override
    @Query("select c from com.example.customers.customer.Customer c left join fetch c.address")
    Set<Customer> findAll();
}
