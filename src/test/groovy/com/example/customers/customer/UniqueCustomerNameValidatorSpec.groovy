package com.example.customers.customer

import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintValidatorContext

class UniqueCustomerNameValidatorSpec extends Specification {

    CustomerService customerService = Mock()

    def tested = new UniqueCustomerNameValidator(customerService)

    @Unroll
    def 'should return confirmation if customer\'s name is unique when name #description'() {
        given:
        customerService.getCustomerByName('existing') >> Optional.of(new Customer())
        customerService.getCustomerByName('notExisting') >> Optional.empty()

        when:
        def result = tested.isValid(customerName, Mock(ConstraintValidatorContext))

        then:
        result == expectedResult

        where:
        description | customerName  | expectedResult
        'exists'    | 'existing'    | false
        'doesn\'t'  | 'notExisting' | true
    }
}
