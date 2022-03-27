package com.example.customers.customer;

import com.example.customers.address.Address;
import com.example.customers.address.AddressDto;
import com.example.customers.address.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(path = "customers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final AddressService addressService;
    private final ObjectMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerAddressDto addCustomer(@Valid @RequestBody CustomerDto request) {
        var toAdd = mapper.convertValue(request, Customer.class);
        return mapper.convertValue(customerService.addCustomer(toAdd), CustomerAddressDto.class);
    }

    @GetMapping
    public Set<CustomerAddressDto> getAllCustomers() {
        return mapper.convertValue(customerService.getAllCustomers(),
                mapper.getTypeFactory().constructCollectionType(Set.class, CustomerAddressDto.class));
    }

    @PutMapping("{customerId}/address")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCustomerAddress(@PathVariable long customerId, @Valid @RequestBody AddressDto request) {
        var toUpdate = mapper.convertValue(request, Address.class);
        addressService.updateCustomerAddress(customerId, toUpdate);
    }
}
