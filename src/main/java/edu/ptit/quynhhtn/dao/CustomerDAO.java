package edu.ptit.quynhhtn.dao;

import edu.ptit.quynhhtn.entity.Customer;
import edu.ptit.quynhhtn.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CustomerDAO extends CrudRepository<Customer, Long> {
    Optional<Customer> findByPersonInfo(Person person);
    Optional<Customer> findByPersonInfo_Username(String username);
}