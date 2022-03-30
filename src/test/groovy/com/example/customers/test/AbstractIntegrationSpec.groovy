package com.example.customers.test

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@SpringBootTest
@TestPropertySource(locations = "classpath:application-for-test.properties")
@AutoConfigureMockMvc
class AbstractIntegrationSpec extends Specification {
    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper
}
