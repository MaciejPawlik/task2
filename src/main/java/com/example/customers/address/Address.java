package com.example.customers.address;

import com.example.customers.customer.Customer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "customer")
@ToString(exclude = "customer")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotEmpty
    @Size(max = 255)
    private String street;

    @Getter
    @Setter
    @NotEmpty
    @Size(max = 255)
    private String city;

    @Getter
    @Setter
    @NotEmpty
    @Column(name = "postal_code")
    @Size(max = 16)
    private String postalCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @Setter
    private Customer customer;
}
