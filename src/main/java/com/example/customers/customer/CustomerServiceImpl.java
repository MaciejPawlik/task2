package com.example.customers.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Customer addCustomer(Customer toAdd) {
        return repository.save(toAdd);
    }

    @Override
    public Set<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(long customerId) {
        return repository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Customer with id: %s not found", customerId)));
    }

    @Override
    public boolean existsCustomerByName(String customerName) {
        return repository.existsByNameIgnoreCase(customerName);
    }
}
