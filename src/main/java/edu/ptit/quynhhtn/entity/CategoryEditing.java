package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class CategoryEditing extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryEditingId;

    private Long categoryId;

    private String oldName;

    @Column(length = 2000)
    private String oldDescription;

    private Long oldParentCategoryId;

    @Column(length = 2000)
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oldParentCategoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)
    private Category oldParentCategory;

    public CategoryEditing() {
    }

    public Long getCategoryEditingId() {
        return categoryEditingId;
    }

    public void setCategoryEditingId(Long categoryEditingId) {
        this.categoryEditingId = categoryEditingId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getOldDescription() {
        return oldDescription;
    }

    public void setOldDescription(String oldDescription) {
        this.oldDescription = oldDescription;
    }

    public Long getOldParentCategoryId() {
        return oldParentCategoryId;
    }

    public void setOldParentCategoryId(Long oldParentCategoryId) {
        this.oldParentCategoryId = oldParentCategoryId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Category getOldParentCategory() {
        return oldParentCategory;
    }

    public void setOldParentCategory(Category oldParentCategory) {
        if(oldParentCategory != null){
            setOldParentCategoryId(oldParentCategory.getCategoryId());
        }
        this.oldParentCategory = oldParentCategory;
    }
}
