package edu.ptit.quynhhtn.service;

import edu.ptit.quynhhtn.dao.OrderDAO;
import edu.ptit.quynhhtn.entity.Cart;
import edu.ptit.quynhhtn.entity.Order;
import edu.ptit.quynhhtn.entity.PaymentInfo;
import edu.ptit.quynhhtn.entity.ShipmentInfo;
import edu.ptit.quynhhtn.form.CheckOutOrderFrm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;

    @Transactional
    public void storeOrder(CheckOutOrderFrm orderFrm, Cart cart){
        PaymentInfo paymentInfo = orderFrm.getPaymentInfo();
        ShipmentInfo shipmentInfo = orderFrm.getShipmentInfo();
        Order newOrder = new Order();
        newOrder.setAddress(shipmentInfo.getAddress());
        newOrder.setContactNo(paymentInfo.getBillingPhone());
        newOrder.setReceiverName(shipmentInfo.getReceiverName());
        newOrder.setCart(cart);
        newOrder.setPaymentInfo(paymentInfo);
        newOrder.setShipmentInfo(shipmentInfo);
        orderDAO.save(newOrder);
//        paymentInfo.setOrder(newOrder);
//        shipmentInfo.setOrder(newOrder);
//        newOrder.setShipmentId(shipmentInfo.getShipmentId());
//        newOrder.setPaymentId(paymentInfo.getPaymentId());
//        newOrder.setCartId(cart.getCartId());

    }
}
