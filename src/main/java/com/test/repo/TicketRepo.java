package com.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entity.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {

}
