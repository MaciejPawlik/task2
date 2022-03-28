package com.example.customers.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @NotEmpty
    @Size(max = 255)
    String street;

    @NotEmpty
    @Size(max = 255)
    String city;

    @NotEmpty
    @Size(max = 16)
    String postalCode;
}
