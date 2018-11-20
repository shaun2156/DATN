package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class ImageUrl extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageUrlId;

    private Long itemId;

    private String url;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "itemId")
    private Item item;

    public ImageUrl() {
    }

    public ImageUrl(String url, Item item) {
        this();
        this.url = url;
        this.item = item;
        this.itemId = item.getItemId();
        item.getImageUrls().add(this);
    }

    public Long getImageUrlId() {
        return imageUrlId;
    }

    public void setImageUrlId(Long imageUrlId) {
        this.imageUrlId = imageUrlId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
