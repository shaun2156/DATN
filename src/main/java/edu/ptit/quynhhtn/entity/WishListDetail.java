package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class WishListDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    private Long itemId;

    private Long wishListId;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId", insertable = false, updatable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "wishListId", insertable = false, updatable = false)
    private WishList wishList;

    public WishListDetail() {
    }

    public WishListDetail(Item item, WishList wishList) {
        this.item = item;
        this.itemId = item.getItemId();
        this.wishList = wishList;
        this.wishListId = wishList.getWishListId();
        wishList.getListDetails().add(this);
    }


    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        if(wishList != null){
            setWishListId(wishList.getWishListId());
        }
        this.wishList = wishList;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        if(item != null){
            setItemId(item.getItemId());
        }
        this.item = item;
    }
}
