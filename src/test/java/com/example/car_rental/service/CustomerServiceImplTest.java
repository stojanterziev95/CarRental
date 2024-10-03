package com.example.car_rental.service;

import com.example.car_rental.models.Customer;
import com.example.car_rental.repository.CustomerRepository;
import com.example.car_rental.serviceImplementation.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer(1L, "John", "Doe", "john.doe@example.com", "1234567890", "D12345678");
    }

    @Test
    void getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> result = customerService.getAllCustomers();
        assertEquals(1, result.size());
        assertEquals(customer, result.get(0));
    }

    @Test
    void getCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1L);
        assertEquals(customer, result);
    }

    @Test
    void getCustomerById_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> customerService.getCustomerById(1L));
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void createCustomer() {
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.createCustomer(customer);
        assertEquals(customer, result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void updateCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer updatedCustomer = new Customer(1L, "Jane", "Doe", "jane.doe@example.com", "0987654321", "D87654321");
        Customer result = customerService.updateCustomer(1L, updatedCustomer);

        assertEquals(updatedCustomer.getFirstName(), result.getFirstName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void deleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);
        customerService.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }
}
