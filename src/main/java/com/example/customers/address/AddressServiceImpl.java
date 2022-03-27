package com.example.customers.address;

import com.example.customers.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    private final CustomerService customerService;

    @Override
    public Address updateCustomerAddress(long customerId, Address updated) {
        var current = repository.findByCustomerId(customerId);
        return current.map(address -> updateAddress(address, updated)).orElseGet(() -> addAddress(updated, customerId));
    }

    private Address addAddress(Address toAdd, long customerId) {
        toAdd.setCustomer(customerService.getCustomerById(customerId));
        return repository.save(toAdd);
    }

    private Address updateAddress(Address current, Address toUpdate) {
        current.setCity(toUpdate.getCity());
        current.setStreet(toUpdate.getStreet());
        current.setPostalCode(toUpdate.getPostalCode());
        return repository.save(current);
    }
}
