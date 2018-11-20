package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class ItemHistory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemHistoryId;

    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "itemId", insertable = false, updatable = false)
    private Item item;

    public Long getItemHistoryId() {
        return itemHistoryId;
    }

    public void setItemHistoryId(Long itemHistoryId) {
        this.itemHistoryId = itemHistoryId;
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
