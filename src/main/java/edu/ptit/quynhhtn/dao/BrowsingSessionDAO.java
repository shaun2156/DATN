package edu.ptit.quynhhtn.dao;

import edu.ptit.quynhhtn.entity.BrowsingSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BrowsingSessionDAO extends CrudRepository<BrowsingSession, Long> {
}