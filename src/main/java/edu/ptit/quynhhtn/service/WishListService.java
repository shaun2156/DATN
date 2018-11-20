package edu.ptit.quynhhtn.service;

import edu.ptit.quynhhtn.dao.WishListDAO;
import edu.ptit.quynhhtn.entity.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishListService {
    @Autowired
    private WishListDAO wishListDAO;

    @Transactional
    public void updateWishList(WishList wishList){
        wishListDAO.save(wishList);
    }
}
