package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class CartItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    private Long cartId;

    private Long itemDetailId;

    private double quantity;

    @ManyToOne
    @JoinColumn(name = "cartId", referencedColumnName = "cartId", insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "itemDetailId", insertable = false, updatable = false)
    private ItemDetail itemDetail;

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(Long itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        if(cart != null){
            setCartId(cart.getCartId());
        }
        this.cart = cart;
    }

    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetail itemDetail) {
        if(itemDetail != null){
            setItemDetailId(itemDetail.getItemDetailId());
        }
        this.itemDetail = itemDetail;
    }
}
