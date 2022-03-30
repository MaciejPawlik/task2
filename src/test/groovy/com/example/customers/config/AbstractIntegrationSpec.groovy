package com.example.customers.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(
        locations = "classpath:application-for-test.properties")
abstract class AbstractIntegrationSpec extends Specification {
    @Shared
    private static PostgreSQLContainer container = new PostgreSQLContainer('postgres:12.10')

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        container.start()
        registry.add('spring.datasource.url', () -> container::getJdbcUrl())
        registry.add('spring.datasource.username', () -> container::getPassword())
        registry.add('spring.datasource.password', () -> container::getUsername())
    }

    def cleanupSpec() {
        container.stop()
    }
}
