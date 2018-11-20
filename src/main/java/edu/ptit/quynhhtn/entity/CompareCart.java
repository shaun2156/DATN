package edu.ptit.quynhhtn.entity;

import java.util.ArrayList;
import java.util.List;

//Sẽ không sử dụng thằng này trong CSDL - chỉ là tạm thời cho mỗi Session của người dùng
public class CompareCart {
    private Long compareCartId;
    private Customer customer;
    private List<Item> itemList;

    public CompareCart() {
        itemList = new ArrayList<>();
    }

    public CompareCart(Customer customer) {
        this();
        this.customer = customer;
    }

    public Long getCompareCartId() {
        return compareCartId;
    }

    public void setCompareCartId(Long compareCartId) {
        this.compareCartId = compareCartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
