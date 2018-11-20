package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.dao.ItemDAO;
import edu.ptit.quynhhtn.dao.PersonDAO;
import edu.ptit.quynhhtn.entity.Category;
import edu.ptit.quynhhtn.entity.CompareCart;
import edu.ptit.quynhhtn.entity.Item;
import edu.ptit.quynhhtn.entity.WishList;
import edu.ptit.quynhhtn.service.StoreConfigUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    @Autowired
    private Category rootCategory;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    @Qualifier(value = "storeConfig")
    private Map<String, String> storeConfig;

    @Autowired
    private StoreConfigUpdateService updateService;

    @ModelAttribute("featured_products")
    public List<Item> featuredProducts(){
        return itemDAO.findTop10ByFeaturedOrderByUpdatedDateDesc(true);
    }

    @ModelAttribute
    public void defaultModelValue(@ModelAttribute("wishList") WishList wishList, Principal principal, Model model){
        updateService.refreshIfNeeded();
        model.addAllAttributes(storeConfig);
        model.addAttribute("rootCategory", rootCategory);
        model.addAttribute("current_user", principal);
        if(principal != null){
            model.addAttribute("current_user_name", personDAO.findPersonByUsername(principal.getName()).orElse(null).getFullName());
        }

    }
}
