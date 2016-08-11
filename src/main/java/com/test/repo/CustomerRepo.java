package com.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>  {

}
