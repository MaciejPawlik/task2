package com.example.customers.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @NotEmpty
    String street;

    @NotEmpty
    String city;

    @NotEmpty
    String postalCode;
}
