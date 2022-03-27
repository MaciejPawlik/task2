package com.example.customers.customer;

import com.example.customers.address.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Optional<Customer> getCustomerByName(String customerName) {
        return repository.findByNameIgnoreCase(customerName);
    }
}
