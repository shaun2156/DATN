package edu.ptit.quynhhtn.dao;

import edu.ptit.quynhhtn.entity.StoreConfig;
import edu.ptit.quynhhtn.entity.WishListDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StoreConfigDAO extends CrudRepository<StoreConfig, Long> {
}