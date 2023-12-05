package com.aj.carserviceticketing.repository;

import com.aj.carserviceticketing.domain.customer.Customer;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Hidden
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findById(UUID uuid);

    Optional<Customer> findByName(String name);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);
}
