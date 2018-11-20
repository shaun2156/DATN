package edu.ptit.quynhhtn.dao;

import edu.ptit.quynhhtn.entity.Customer;
import edu.ptit.quynhhtn.entity.WishList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface WishListDAO extends CrudRepository<WishList, Long> {
    Optional<WishList> findByCustomer(Customer cust);
}