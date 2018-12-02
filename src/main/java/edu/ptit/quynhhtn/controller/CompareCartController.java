package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.dao.ItemDAO;
import edu.ptit.quynhhtn.entity.CompareCart;
import edu.ptit.quynhhtn.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
@SessionAttributes("compareCart")
public class CompareCartController {
    @Autowired
    ItemDAO itemDAO;

    @GetMapping("/addToCompareCart")
    @ResponseBody
    public String addToCompareCart(@RequestParam Long item,CompareCart compareCart){
        Item selected = itemDAO.findById(item).orElse(null);
        if(selected != null && compareCart.getItemList().stream().noneMatch(listItem -> listItem.getItemId().equals(item))){
            compareCart.getItemList().add(selected);
        }
        return selected.getName();
    }

    @GetMapping("/removeFromCompareCart")
    public String removeFromCompareCart(@RequestParam Long itemId,CompareCart compareCart){
        compareCart.getItemList().removeIf(item -> item.getItemId().equals(itemId));
        return "redirect:compare.html";
    }

    @GetMapping("/compare.html")
    public String viewCompareCart(CompareCart compareCart, Model model){
        return "compare";
    }
}
