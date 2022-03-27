package com.example.customers.customer

import spock.lang.Specification

import javax.persistence.EntityNotFoundException

class CustomerServiceImplSpec extends Specification {

    CustomerRepository repository = Mock()

    def tested = new CustomerServiceImpl(repository)

    def 'should add new customer'() {
        given:
        def customerToAdd = new Customer(null, 'Customer', null)
        def expected = new Customer(5L, 'Customer', null)
        repository.save(customerToAdd) >> expected

        when:
        def result = tested.addCustomer(customerToAdd)

        then:
        result == expected
    }

    def 'should find all customers'() {
        given:
        def expected = [new Customer(5L, 'Customer', null),
                        new Customer(33L, 'Customer', null)].toSet()
        repository.findAll() >> expected

        when:
        def result = tested.getAllCustomers()

        then:
        result == expected
    }

    def 'should find customer'() {
        given:
        def existing = new Customer(5L, 'Customer', null)
        repository.findById(5L) >> Optional.of(existing)

        when:
        def result = tested.getCustomerById(5L)

        then:
        result == existing
    }

    def 'should throw exception because customer doesn\'t exist'() {
        given:
        repository.findById(5L) >> Optional.empty()

        when:
        tested.getCustomerById(5L)

        then:
        thrown EntityNotFoundException
    }
}
