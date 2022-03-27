package com.example.customers.customer;

import com.example.customers.address.Address;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "address")
@ToString(exclude = "address")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private Address address;
}
