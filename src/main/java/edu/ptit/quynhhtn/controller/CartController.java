package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.config.SiteConfig;
import edu.ptit.quynhhtn.dao.CustomerDAO;
import edu.ptit.quynhhtn.dao.ItemDAO;
import edu.ptit.quynhhtn.entity.*;
import edu.ptit.quynhhtn.form.CheckOutOrderFrm;
import edu.ptit.quynhhtn.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@ControllerAdvice
@SessionAttributes("cart")
public class CartController {
    @Autowired
    ItemDAO itemDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    OrderService orderService;

    Cart cart;

    CheckOutOrderFrm conversationFrm;

    @GetMapping("/addToCart")
    @ResponseBody
    public String addToCart(@RequestParam Long item, Cart cart) {
        Item selected = itemDAO.findById(item).orElse(null);
        ItemDetail detail = selected.getItemDetails().stream().filter(det -> det.getQuantity() > 0).findFirst().orElse(null);
        if (detail != null) {
            CartItem cartItem;
            cartItem = cart.getCartItemList().stream()
                    .filter(items -> items.getItemDetail().getItemId().equals(selected.getItemId())).findFirst().orElse(null);
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setItemDetail(detail);
                cartItem.setQuantity(1);
                cart.getCartItemList().add(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            }
            cart.calculateTotalPrice();
        }
        return selected.getName();
    }

    @GetMapping("/removeCart")
    @ResponseBody
    public String removeCart(@RequestParam(name = "item") Long item) {
        cart.getCartItemList().removeIf(cartItem -> cartItem.getItemDetail().getItem().getItemId() == item);
        cart.calculateTotalPrice();
        return "OK";
    }

    @GetMapping("/checkout.html")
    public String viewCartDetails(Principal principal,
                                  @ModelAttribute CheckOutOrderFrm orderFrm,
                                  Model model) {
        if(principal == null) return "register-login";
        Person user = SiteConfig.getInstance().getLoggedInUser();
        orderFrm.getPaymentInfo().setBillingName(user.getFullName());
        orderFrm.getPaymentInfo().setBillingEmail(user.getEmail());
        orderFrm.getPaymentInfo().setBillingPhone(user.getPhone());
        orderFrm.getPaymentInfo().setPaymentOption("bank");
        orderFrm.getPaymentInfo().setPaymentTerm("bank");
        orderFrm.getShipmentInfo().setReceiverName(user.getFullName());
        conversationFrm = orderFrm;
        model.addAttribute("orderFrm", conversationFrm);
        return "checkout";
    }

    @PostMapping("/checkout.html")
    public String updateCartDetails(@ModelAttribute CheckOutOrderFrm orderFrm,
                                    @RequestParam(value = "payment_method", required = false) String paymentMethod,
                                    Model model) {
        if (StringUtils.isEmpty(paymentMethod)) {
            if (!orderFrm.isEmptyShipmentInfo()) {
                conversationFrm.setShipmentInfo(orderFrm.getShipmentInfo());
            }
            if (!orderFrm.isEmptyPaymentInfo()) {
                String payment = conversationFrm.getPaymentInfo().getPaymentOption();
                conversationFrm.setPaymentInfo(orderFrm.getPaymentInfo());
                conversationFrm.getPaymentInfo().setPaymentOption(payment);
                conversationFrm.getPaymentInfo().setPaymentTerm(payment);
            }
        } else {
            String payment = orderFrm.getPaymentInfo().getPaymentOption();
            conversationFrm.getPaymentInfo().setPaymentOption(payment);
            conversationFrm.getPaymentInfo().setPaymentTerm(payment);
        }
        model.addAttribute("orderFrm", conversationFrm);
        return "checkout";
    }

    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute CheckOutOrderFrm orderFrm, Model model) {
        orderService.storeOrder(conversationFrm, cart);
        cart = new Cart();
        model.addAttribute("cart", cart);
        return "redirect:index.html";
    }

    @GetMapping("/cart.html")
    public String defaultStaticHandler(Cart cart) {
        return "cart";
    }


    @ModelAttribute(name = "cart")
    public Cart sessionCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }
}
