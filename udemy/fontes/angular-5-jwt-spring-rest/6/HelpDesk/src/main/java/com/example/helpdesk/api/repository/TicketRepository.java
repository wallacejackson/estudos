package com.example.helpdesk.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.helpdesk.api.entity.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String> {

	public Page<Ticket> findByUserIdOrderByDateDesc(String id, Pageable page);

	public Page<Ticket> findByTitleIgnoreCaseContainingAndStatusContainingAndPriorityContainingOrderByDateDesc(
			String title, String status, String priority, Pageable pages);

	public Page<Ticket> findByTitleIgnoreCaseContainingAndStatusContainingAndPriorityContainingAndUserIdOrderByDateDesc(
			String title, String status, String priority, String userId, Pageable pages);

	public Page<Ticket> findByTitleIgnoreCaseContainsAndStatusContainsAndPriorityContainsAndAssignedUserIdOrderByDateDesc(
			String title, String status, String priority, String assignedUserId, Pageable pages);

	public Page<Ticket> findByNumber(Integer number, Pageable pages);

}
