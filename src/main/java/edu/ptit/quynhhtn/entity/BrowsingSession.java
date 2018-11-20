package edu.ptit.quynhhtn.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class BrowsingSession extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long browsingSessionId;

    private Long customerId;

    private Long duration;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "itemHistoryId")
    private List<ItemHistory> itemHistoryList;

    @ManyToOne
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    private Customer customer;

    public Long getBrowsingSessionId() {
        return browsingSessionId;
    }

    public void setBrowsingSessionId(Long browsingSessionId) {
        this.browsingSessionId = browsingSessionId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public List<ItemHistory> getItemHistoryList() {
        return itemHistoryList;
    }

    public void setItemHistoryList(List<ItemHistory> itemHistoryList) {
        this.itemHistoryList = itemHistoryList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if(customer!= null){
            setCustomerId(customer.getCustomerId());
        }
        this.customer = customer;
    }
}
