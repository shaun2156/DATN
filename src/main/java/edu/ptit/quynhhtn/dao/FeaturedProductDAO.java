package edu.ptit.quynhhtn.dao;

import edu.ptit.quynhhtn.entity.FeaturedProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FeaturedProductDAO extends CrudRepository<FeaturedProduct, Long> {
    Iterable<FeaturedProduct> findAllByEnabledTrue();
}