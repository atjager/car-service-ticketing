package com.aj.carserviceticketing.repository;

import com.aj.carserviceticketing.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findById(UUID uuid);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);
}
