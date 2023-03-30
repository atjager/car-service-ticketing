package com.aj.carserviceticketing.controller;

import com.aj.carserviceticketing.domain.customer.Customer;
import com.aj.carserviceticketing.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer/")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("create")
    private Customer createCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("all")
    private List<Customer> getAll() {
        return customerService.findAll();
    }

    @PutMapping("update")
    private Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.update(customer);
    }
}
