package com.example.customers.address;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findByCustomerId(long customerId);
}
