package com.example.cbsmiddleware.Customer.Repositories;


import com.example.cbsmiddleware.Customer.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findById(Integer CustomerId);

    public Customer findCustomerByEmail(String email);
    public Customer findCustomersByMobile(String mobile);
    public Customer findCustomersByCin(Integer cin);
    public Customer findCustomersByFax(String fax);
    List<Customer> findByLastName(String lastname);
}
