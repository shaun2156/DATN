package edu.ptit.quynhhtn.entity;

public class TestProduct {
    private Long id;
    private String imgUrl;
    private Category category;
    private String productName;
    private String shortDesc;
    private String description;
    private double currentPrice;
    private double oldPrice;
    private double rating;
    private boolean inStock;

    public TestProduct(Long id, String imgUrl, Category category, String productName, String shortDesc, String description, double currentPrice, double oldPrice, double rating, boolean inStock) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.category = category;
        this.productName = productName;
        this.shortDesc = shortDesc;
        this.description = description;
        this.currentPrice = currentPrice;
        this.oldPrice = oldPrice;
        this.rating = rating;
        this.inStock = inStock;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
