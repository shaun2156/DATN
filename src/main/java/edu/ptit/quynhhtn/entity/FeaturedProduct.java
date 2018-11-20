package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class FeaturedProduct extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private boolean enabled;

    private Long itemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId", insertable = false, updatable = false)
    private Item item;

    public FeaturedProduct(boolean enabled, Long itemId, Item item) {
        this.enabled = enabled;
        this.itemId = itemId;
        this.item = item;
    }

    public FeaturedProduct() {
    }

    public FeaturedProduct(boolean enabled, Item item) {
        this.enabled = enabled;
        this.item = item;
        this.itemId = item.getItemId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
