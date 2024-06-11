package com.uep.wap.repository;
import com.uep.wap.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c WHERE c.creationDate <= ?1")
    List<Customer> findOldCustomers(LocalDate date);

    List<Customer> findAllByOrderByName();

    List<Customer> findAllByOrderBySurname();
}
