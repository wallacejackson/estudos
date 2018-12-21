package com.example.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.helpdesk.api.entity.ChangeStatus;
import com.example.helpdesk.api.entity.Ticket;
import com.example.helpdesk.api.repository.ChangeStatusRepository;
import com.example.helpdesk.api.repository.TicketRepository;
import com.example.helpdesk.api.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository repository;

	@Autowired
	private ChangeStatusRepository changeStatusRepository;

	@Override
	public Ticket createOrUpdate(Ticket ticket) {
		ticket.setId(ticket.getId() == "" ? null : ticket.getId());
		return this.repository.save(ticket);
	}

	@Override
	public Ticket findById(String id) {
		return this.repository.findById(id).get();
	}

	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}

	@Override
	public Page<Ticket> listTicket(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return this.repository.findAll(pages);
	}

	@Override
	public ChangeStatus createChangeStatus(ChangeStatus change) {
		return this.changeStatusRepository.save(change);
	}

	@Override
	public Iterable<ChangeStatus> listChangeStatus(String ticketId) {
		return this.changeStatusRepository.findByTicketIdOrderByDateChangeStatusDesc(ticketId);
	}

	@Override
	public Page<Ticket> findByCurrentUser(int page, int count, String userId) {
		Pageable pages = PageRequest.of(page, count);
		return this.repository.findByUserIdOrderByDateDesc(userId, pages);
	}

	@Override
	public Page<Ticket> findByParameters(int page, int count, String title, String status, String priority) {
		Pageable pages = PageRequest.of(page, count);
		return this.repository.findByTitleIgnoreCaseContainingAndStatusContainingAndPriorityContainingOrderByDateDesc(
				title, status, priority, pages);
	}

	@Override
	public Page<Ticket> findParametersAndCurrentUser(int page, int count, String title, String status, String priority,
			String userId) {
		Pageable pages = PageRequest.of(page, count);
		return this.repository
				.findByTitleIgnoreCaseContainingAndStatusContainingAndPriorityContainingAndUserIdOrderByDateDesc(title,
						status, priority, userId, pages);
	}

	@Override
	public Page<Ticket> findByNumber(int page, int count, Integer number) {
		Pageable pages = PageRequest.of(page, count);
		return this.repository.findByNumber(number, pages);
	}

	@Override
	public Iterable<Ticket> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Page<Ticket> findByParameterAndAssignedUser(int page, int count, String title, String status,
			String priority, String assignedUse) {
		Pageable pages = PageRequest.of(page, count);
		return this.repository
				.findByTitleIgnoreCaseContainsAndStatusContainsAndPriorityContainsAndAssignedUserIdOrderByDateDesc(
						title, status, priority, assignedUse, pages);
	}

}
