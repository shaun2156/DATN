package edu.ptit.quynhhtn.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ShipmentInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;

    private Long orderId;

    private Long shmServiceId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date plannedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualDate;

    private String address;

    private String receiverName;

    @OneToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "shmServiceId", insertable = false, updatable = false)
    private ShipmentService shipmentService;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShmServiceId() {
        return shmServiceId;
    }

    public void setShmServiceId(Long shmServiceId) {
        this.shmServiceId = shmServiceId;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        if(order != null){
            setOrderId(order.getOrderId());
        }
        this.order = order;
    }

    public ShipmentService getShipmentService() {
        return shipmentService;
    }

    public void setShipmentService(ShipmentService shipmentService) {
        if(shipmentService != null){
            setShmServiceId(shipmentService.getShmServiceId());
        }
        this.shipmentService = shipmentService;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
