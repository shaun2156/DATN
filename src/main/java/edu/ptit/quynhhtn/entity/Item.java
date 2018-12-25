package edu.ptit.quynhhtn.entity;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private Long categoryId;

    private String name;

    @Column(length = 2000)
    private String description;

    private String shortDescription;

    private double currentPrice;

    private double originalPrice;

    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ImageUrl> imageUrls;

    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ItemDetail> itemDetails;

    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;

    @Column(columnDefinition="bit default 0")
    private boolean featured = false;

    public double getRating(){
        if(CollectionUtils.isEmpty(comments)) return 0;
        return comments.stream().mapToDouble(Comment::getRating).average().orElse(5);
    }

    public boolean isInStock(){
        return !(CollectionUtils.isEmpty(itemDetails) || itemDetails.stream().mapToInt(ItemDetail::getStockRemain).sum() == 0);
    }

    public Item() {
        itemDetails = new ArrayList<>();
        imageUrls = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public Item(String name, String description, String shortDescription, double currentPrice, double originalPrice, Category category) {
        this();
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.currentPrice = currentPrice;
        this.originalPrice = originalPrice;
        this.categoryId = category == null? null :category.getCategoryId();
        if(category != null) category.getListItems().add(this);
    }

    public Item(String name, String description, String shortDescription, double currentPrice, double originalPrice) {
        this();
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.currentPrice = currentPrice;
        this.originalPrice = originalPrice;
    }

    public String getListSize(){
        return itemDetails.stream().map(ItemDetail::getSize).distinct() .collect(Collectors.joining(", "));
    }

    //========= NORMAL GETTER/SETTER
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if(category != null){
            setCategoryId(category.getCategoryId());
        }
        this.category = category;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public List<ImageUrl> getImageUrls() {
        if (CollectionUtils.isEmpty(imageUrls)) {
            imageUrls.add(new ImageUrl());
        }
        return imageUrls;
    }

    public void setImageUrls(List<ImageUrl> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getDisplayComments(){
        return getComments()
                .stream()
                .sorted((c1,c2)-> DateUtils.truncatedCompareTo(c2.getCreatedDate(),c1.getCreatedDate(), Calendar.SECOND))
                .limit(5).collect(Collectors.toList());
    }
}
