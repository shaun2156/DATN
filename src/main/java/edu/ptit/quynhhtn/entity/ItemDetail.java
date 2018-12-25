package edu.ptit.quynhhtn.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ItemDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemDetailId;

    @NotNull
    private Long itemId;

    private String size;

    private String color;

    private Integer stockRemain;

    private double cost;

    @ManyToOne
    @JoinColumn(name = "itemId", insertable = false, updatable = false)
    private Item item;

    public ItemDetail() {
    }

    public ItemDetail(String size, String color, Integer stockRemain, Item item) {
        this.size = size;
        this.color = color;
        this.stockRemain = stockRemain;
        this.itemId = item == null ? null : item.getItemId();
        if (item != null) {
            item.getItemDetails().add(this);
        }
    }

    public Long getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(Long itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getStockRemain() {
        return stockRemain;
    }

    public void setStockRemain(Integer stockRemain) {
        this.stockRemain = stockRemain;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
