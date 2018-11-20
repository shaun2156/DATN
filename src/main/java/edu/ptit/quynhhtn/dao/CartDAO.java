package edu.ptit.quynhhtn.dao;

import edu.ptit.quynhhtn.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CartDAO extends CrudRepository<Cart, Long> {
}