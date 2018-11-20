package edu.ptit.quynhhtn.entity;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    private String description;

    private Long parentCategoryId;

    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category parentCategory;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parentCategoryId")
    private List<Category> subCategories;

    @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> listItems;

    public Category(Long id, String name){
        this();
        this.categoryId = id;
        this.name = name;
    }

    public Category(){
        listItems = new ArrayList<>();
        subCategories = new ArrayList<>();
    }

    public Category(String name, String description, Long parentCategoryId) {
        this();
        this.name = name;
        this.description = description;
        this.parentCategoryId = parentCategoryId;
    }

    public Category(String name, String description, Category parentCategory) {
        this();
        this.name = name;
        this.description = description;
        this.parentCategoryId = parentCategory == null ? null : parentCategory.getCategoryId();
        if(parentCategory != null){
            parentCategory.getSubCategories().add(this);
        }
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        if(parentCategory != null){
            setParentCategoryId(parentCategory.getCategoryId());
        }
        this.parentCategory = parentCategory;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public List<Item> getListItems() {
        return listItems;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getCategoryId(), category.getCategoryId()) &&
                Objects.equals(getName(), category.getName()) &&
                Objects.equals(getParentCategoryId(), category.getParentCategoryId()) &&
                getSubCategories().containsAll(category.getSubCategories()) && category.getSubCategories().containsAll(getSubCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getName(), getParentCategoryId(), getSubCategories());
    }
}
