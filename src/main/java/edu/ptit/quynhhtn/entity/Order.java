package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_order")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long cartId;

    private Long paymentId;

    private Long shipmentId;

    private String address;

    private String contactNo;

    private String receiverName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "paymentId", insertable = false, updatable = false)
    private PaymentInfo paymentInfo;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipmentId", insertable = false, updatable = false)
    private ShipmentInfo shipmentInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", insertable = false, updatable = false)
    private Cart cart;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }


    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        if(paymentInfo != null){
            setPaymentId(paymentInfo.getPaymentId());
        }
        this.paymentInfo = paymentInfo;
    }

    public ShipmentInfo getShipmentInfo() {
        return shipmentInfo;
    }

    public void setShipmentInfo(ShipmentInfo shipmentInfo) {
        if(shipmentInfo != null){
            setShipmentId(shipmentInfo.getShipmentId());
        }
        this.shipmentInfo = shipmentInfo;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        if(cart != null){
            setCartId(cart.getCartId());
        }
        this.cart = cart;
    }
}
