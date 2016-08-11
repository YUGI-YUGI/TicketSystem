package com.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entity.Commant;
import com.test.entity.Ticket;
@Repository
public interface CommantRepo extends JpaRepository<Commant, Long> {
	
 public List<Commant>  findByTId(Ticket tId);
}
