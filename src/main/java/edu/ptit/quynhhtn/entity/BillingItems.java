package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class BillingItems extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billingItemId;

    private long itemDetailId;

    private double price;

    private long billingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemDetailId", referencedColumnName = "itemDetailId", insertable = false, updatable = false)
    private ItemDetail itemDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billingId", referencedColumnName = "billingId", insertable = false, updatable = false)
    private Billing billing;

    public BillingItems() {
    }

    public long getBillingItemId() {
        return billingItemId;
    }

    public void setBillingItemId(long billingItemId) {
        this.billingItemId = billingItemId;
    }

    public long getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(long itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getBillingId() {
        return billingId;
    }

    public void setBillingId(long billingId) {
        this.billingId = billingId;
    }

    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        if(billing != null){
            setBillingId(billing.getBillingId());
        }
        this.billing = billing;
    }
}
