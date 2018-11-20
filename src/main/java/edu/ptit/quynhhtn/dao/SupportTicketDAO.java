package edu.ptit.quynhhtn.dao;

import edu.ptit.quynhhtn.entity.SupportTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SupportTicketDAO extends CrudRepository<SupportTicket, Long> {
}