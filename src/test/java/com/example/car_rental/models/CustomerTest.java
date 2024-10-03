package com.example.car_rental.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    public void testCustomerGettersAndSetters() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("123-456-7890");
        customer.setDriverLicenseNumber("D1234567");

        assertEquals(1L, customer.getId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("123-456-7890", customer.getPhoneNumber());
        assertEquals("D1234567", customer.getDriverLicenseNumber());
    }
}
