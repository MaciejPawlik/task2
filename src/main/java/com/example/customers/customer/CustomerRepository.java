package com.example.customers.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Override
    @Query("select c from com.example.customers.customer.Customer c left join fetch c.address")
    Set<Customer> findAll();

    Optional<Customer> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}
