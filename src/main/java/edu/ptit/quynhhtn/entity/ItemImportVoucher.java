package edu.ptit.quynhhtn.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ItemImportVoucher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long importingId;

    private Long storageId;

    private Long supplierId;

    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId", insertable = false, updatable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "storageId", insertable = false, updatable = false)
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "supplierId", insertable = false, updatable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "itemImportId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImportDetail> itemDetails;

    public Long getImportingId() {
        return importingId;
    }

    public void setImportingId(Long importingId) {
        this.importingId = importingId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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
        this.item = item;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        if(supplier != null){
            setSupplierId( supplier.getSupplierId());
        }
        this.supplier = supplier;
    }
}
