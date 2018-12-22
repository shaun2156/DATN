package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class ItemImportDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemImportDetailId;

    private String size;

    private String color;

    private Integer quantity;

    private double cost;

    private Long itemImportId;

    @ManyToOne
    @JoinColumn(name = "itemImportId", referencedColumnName = "importingId", insertable = false, updatable = false)
    private ItemImporting itemImporting;

    public ItemImportDetail() {
    }

    public Long getItemImportId() {
        return itemImportId;
    }

    public void setItemImportId(Long itemImportId) {
        this.itemImportId = itemImportId;
    }

    public Long getItemImportDetailId() {
        return itemImportDetailId;
    }

    public void setItemImportDetailId(Long itemImportDetailId) {
        this.itemImportDetailId = itemImportDetailId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemImporting getItemImporting() {
        return itemImporting;
    }

    public void setItemImporting(ItemImporting itemImporting) {
        if (itemImporting != null) {
            setItemImportId(itemImporting.getImportingId());
        }
        this.itemImporting = itemImporting;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
