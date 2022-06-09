package com.example.customers.customer

import com.example.customers.address.AddressDto
import com.example.customers.config.AbstractControllerIntegrationSpec
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

class CustomerControllerIntegrationSpec extends AbstractControllerIntegrationSpec {

    def 'should add new customer'() {
        given:
        def toSave = new CustomerDto('testName')

        when:
        def result = mockMvc.perform(
                post('/customers')
                        .header('authorization', 'Basic YWRtaW46YWRtaW4=')
                        .content(objectMapper.writeValueAsString(toSave))
                        .contentType(MediaType.APPLICATION_JSON))
        .andReturn().response

        then:
        result.status == HttpStatus.CREATED.value()
        def response = objectMapper.readValue(result.getContentAsString(), CustomerAddressDto.class)
        with(response) {
            name == toSave.name
            address == null
        }
    }

    def 'should get customers list'() {
        when:
        def result = mockMvc.perform(
                get('/customers')
                        .header('authorization', 'Basic YWRtaW46YWRtaW4='))
                .andReturn().response

        then:
        result.status == HttpStatus.OK.value()
        result != null
    }

    def 'shouldn\'t add new customer because it exists'() {
        given:
        def toSave = new CustomerDto('existing')
        mockMvc.perform(
                post('/customers')
                        .header('authorization', 'Basic YWRtaW46YWRtaW4=')
                        .content(objectMapper.writeValueAsString(toSave))
                        .contentType(MediaType.APPLICATION_JSON))

        when:
        def result = mockMvc.perform(
                post('/customers')
                        .header('authorization', 'Basic YWRtaW46YWRtaW4=')
                        .content(objectMapper.writeValueAsString(toSave))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then:
        result.status == HttpStatus.BAD_REQUEST.value()
    }

    def 'should add address to customer'() {
        given:
        def customer = new CustomerDto('with Address')
        def addCustomerRequestResult = mockMvc.perform(
                post('/customers')
                        .header('authorization', 'Basic YWRtaW46YWRtaW4=')
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        def customerId = objectMapper.readValue(addCustomerRequestResult.getContentAsString(), CustomerAddressDto.class).id

        def addressToAdd = new AddressDto('Street', 'City', '43-300')

        when:
        def result = mockMvc.perform(
                put("/customers/${customerId}/address")
                        .header('authorization', 'Basic YWRtaW46YWRtaW4=')
                        .content(objectMapper.writeValueAsString(addressToAdd))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then:
        result.status == HttpStatus.NO_CONTENT.value()
    }

    def 'shouldn\'t add address to customer that doesn\'t exist'() {
        given:
        def addressToAdd = new AddressDto('Street', 'City', '43-300')

        when:
        def result = mockMvc.perform(
                put("/customers/1243/address")
                        .header('authorization', 'Basic YWRtaW46YWRtaW4=')
                        .content(objectMapper.writeValueAsString(addressToAdd))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then:
        result.status == HttpStatus.NOT_FOUND.value()
    }
}
