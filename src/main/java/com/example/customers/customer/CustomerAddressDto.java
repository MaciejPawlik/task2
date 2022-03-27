package com.example.customers.customer;

import com.example.customers.address.AddressDto;
import lombok.Value;

@Value
public class CustomerAddressDto {
    long id;

    String name;

    AddressDto address;
}
