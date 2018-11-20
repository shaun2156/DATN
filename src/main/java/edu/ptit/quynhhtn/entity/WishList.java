package edu.ptit.quynhhtn.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WishList extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishListId;

    private Long customerId;

    private double totalPrice;

    @OneToMany(mappedBy = "wishListId",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishListDetail> listDetails;

    @ManyToOne
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    private Customer customer;


    public WishList() {
        listDetails = new ArrayList<>();
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<WishListDetail> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<WishListDetail> listDetails) {
        this.listDetails = listDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if(customer != null){
            setCustomerId(customer.getCustomerId());
        }
        this.customer = customer;
    }
}
