package com.aj.carserviceticketing.service;

import com.aj.carserviceticketing.domain.customer.Customer;
import com.aj.carserviceticketing.exception.ItemAlreadyExistsException;
import com.aj.carserviceticketing.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) throws ItemAlreadyExistsException {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent() || customerRepository.findByPhone(customer.getPhone()).isPresent()) {
            throw new ItemAlreadyExistsException("", "");
        }
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer update(Customer customer) {
        Customer customerOld = customerRepository.findById(customer.getId()).get();
        customer.setCreated(customerOld.getCreated());
        return customerRepository.save(customer);
    }

    public void delete(String id) {
        customerRepository.delete(customerRepository.findById(UUID.fromString(id)).get());
    }
}
