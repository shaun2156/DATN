package edu.ptit.quynhhtn.dao;

import edu.ptit.quynhhtn.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ItemDAO extends CrudRepository<Item, Long> {
    List<Item> findAllByFeaturedOrderByUpdatedDateDesc(boolean featured);
    List<Item> findTop10ByFeaturedOrderByUpdatedDateDesc(boolean featured);
    List<Item> findTop10ByOrderByCreatedDateDesc();
    List<Item> findAllByCategoryId(Long categoryId);
    List<Item> findAllByNameContaining(String query);
    Page<Item> findAllByNameContaining(String query, Pageable pageable);
    @Query(nativeQuery=true, value="SELECT *  FROM item i WHERE i.category_id = ?1 ORDER BY rand() LIMIT 3")
    List<Item> findRandom3ItemByCategory(Long categoryId);

    @Query(nativeQuery=true, value="SELECT *  FROM item i WHERE i.category_id = ?1 and i.name LIKE CONCAT('%',?2,'%')")
    List<Item> findAllByCategoryIdAndQuery(Long categoryId, String query);
}