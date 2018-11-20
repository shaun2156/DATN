package edu.ptit.quynhhtn.dao;

import edu.ptit.quynhhtn.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CategoryDAO extends CrudRepository<Category, Long> {
    Optional<Category> findCategoryByName(String name);
}