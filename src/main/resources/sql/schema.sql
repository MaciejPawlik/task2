CREATE TABLE IF NOT EXISTS customers (
    id              bigserial CONSTRAINT customers_pk PRIMARY KEY,
    name            VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS addresses (
    id              bigserial CONSTRAINT addresses_pk PRIMARY KEY,
    street          VARCHAR(255) NOT NULL,
    city            VARCHAR(255) NOT NULL,
    postal_code     VARCHAR(16) NOT NULL,
    customer_id     bigint NOT NULL,
    CONSTRAINT addresses_customer_fk FOREIGN KEY (customer_id) REFERENCES customers(id)
);