package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.dao.ItemDAO;
import edu.ptit.quynhhtn.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@SessionAttributes("cart")
public class CartController {
    @Autowired
    ItemDAO itemDAO;

    @GetMapping("/addToCart")
    @ResponseBody
    public String addToCart(@RequestParam Long item, HttpServletRequest request){
        Item selected = itemDAO.findById(item).orElse(null);
        return selected.getName();
    }

    @GetMapping("/checkout.html")
    public String defaultStaticHandler(Principal principal, Model model){
        if(principal == null) return "register-login";
        return "checkout";
    }
}
