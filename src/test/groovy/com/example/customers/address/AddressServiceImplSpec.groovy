package com.example.customers.address

import com.example.customers.customer.Customer
import com.example.customers.customer.CustomerService
import spock.lang.Specification

class AddressServiceImplSpec extends Specification {

    AddressRepository repository = Mock()
    CustomerService customerService = Mock()

    def tested = new AddressServiceImpl(repository, customerService)

    def 'should add new address if it does not exists'() {
        given:
        def customer = new Customer(5L, 'Customer', null)
        def addressToAdd = new Address(null, 'Street', 'City', '22-333', null)
        def addressToSave = new Address(null, 'Street', 'City', '22-333', customer)
        def expected = new Address(33L, 'Street', 'City', '22-333', customer)

        repository.findByCustomerId(5L) >> Optional.empty()
        repository.save(addressToSave) >> expected

        when:
        def result = tested.updateCustomerAddress(5L, addressToAdd)

        then:
        result == expected
    }

    def 'should add update address if it exists'() {
        given:
        def currentAddress = new Address(33L, 'Street', 'City', '22-333', null)
        def customer = new Customer(5L, 'Customer', currentAddress)
        def addressToUpdate = new Address(null, 'Street2', 'City2', '22-444', null)
        def addressToSave = new Address(33L, 'Street2', 'City2', '22-444', customer)
        def expected = new Address(33L, 'Street2', 'City2', '22-444', customer)

        repository.findByCustomerId(5L) >> Optional.of(currentAddress)
        repository.save(addressToSave) >> expected

        when:
        def result = tested.updateCustomerAddress(5L, addressToUpdate)

        then:
        result == expected
    }
}
