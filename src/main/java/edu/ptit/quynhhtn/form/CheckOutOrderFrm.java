package edu.ptit.quynhhtn.form;

import edu.ptit.quynhhtn.entity.PaymentInfo;
import edu.ptit.quynhhtn.entity.ShipmentInfo;

public class CheckOutOrderFrm {
    private PaymentInfo paymentInfo;
    private ShipmentInfo shipmentInfo;

    public CheckOutOrderFrm() {
    }

    public PaymentInfo getPaymentInfo() {
        if (paymentInfo == null) {
            paymentInfo = new PaymentInfo();
        }
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public ShipmentInfo getShipmentInfo() {
        if (shipmentInfo == null) {
            shipmentInfo = new ShipmentInfo();
        }
        return shipmentInfo;
    }

    public void setShipmentInfo(ShipmentInfo shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }

    public boolean isEmptyPaymentInfo() {
        return paymentInfo == null;
    }

    public boolean isEmptyShipmentInfo() {
        return shipmentInfo == null;
    }
}
