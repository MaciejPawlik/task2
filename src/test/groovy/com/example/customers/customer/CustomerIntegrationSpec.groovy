package com.example.customers.customer

import com.example.customers.config.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class CustomerIntegrationSpec extends AbstractIntegrationSpec {

    @Autowired
    CustomerService tested

    def 'should add new Customer'() {
        given:
        def toSave = new Customer(null, "testName", null)

        when:
        def result = tested.addCustomer(toSave)

        then:
        with(result) {
            id != null
            name == toSave.name
            address == null
        }
    }

    def 'should find all customers'() {
        when:
        def result = tested.getAllCustomers()

        then:
        result.size() == 1
    }
}
