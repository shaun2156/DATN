package edu.ptit.quynhhtn.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Billing extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billingId;

    private Long orderId;

    private Long processedBy;

    private double totalPrice;

    @OneToMany(mappedBy = "billingId", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BillingItems> listBillingItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="processedBy", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employee processedByEmployee;

    public Billing() {
    }

    public Long getBillingId() {
        return billingId;
    }

    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(Long processedBy) {
        this.processedBy = processedBy;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BillingItems> getListBillingItems() {
        return listBillingItems;
    }

    public void setListBillingItems(List<BillingItems> listBillingItems) {
        this.listBillingItems = listBillingItems;
    }

    public Employee getProcessedByEmployee() {
        return processedByEmployee;
    }

    public void setProcessedByEmployee(Employee processedByEmployee) {
        if(processedByEmployee != null){
            setProcessedBy(processedByEmployee.getEmployeeId());
        }
        this.processedByEmployee = processedByEmployee;
    }
}
