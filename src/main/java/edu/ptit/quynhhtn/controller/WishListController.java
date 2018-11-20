package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.dao.CustomerDAO;
import edu.ptit.quynhhtn.dao.ItemDAO;
import edu.ptit.quynhhtn.dao.WishListDAO;
import edu.ptit.quynhhtn.entity.*;
import edu.ptit.quynhhtn.service.WishListService;
import edu.ptit.quynhhtn.utils.CustomCollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class WishListController {
    @Autowired
    ItemDAO itemDAO;

    @Autowired
    WishListDAO wishListDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    Person currentPerson;

    @Autowired
    WishListService wishListService;

    WishList wishList;

    @ModelAttribute("wishList")
    public WishList wishListSession() {
        return wishList;
    }

    @GetMapping("/addToWishList")
    @ResponseBody
    @Transactional
    public String addToWishList(@RequestParam Long item, Principal principal) {
        if(principal == null){
            return "<h5>&nbsp;</h5><h6>Bạn cần đăng nhập để thêm vào danh sách yêu thích</h6>";
        }
        initWishList(principal);
        String response = "<h5>Sản phẩm <span>%s</span></h5>\n" +
                "        <h6>Đã được thêm vào danh sách yêu thích</h6>";
        Item selected = itemDAO.findById(item).orElse(null);
        if (selected != null) {
            wishList = wishListDAO.findById(wishList.getWishListId()).orElse(null);
            if (wishList.getListDetails().stream().noneMatch(det -> Objects.equals(det.getItemId(), item))) {
                new WishListDetail(selected, wishList);
            }
        }

        return String.format(response,selected.getName());
    }

    private void initWishList(Principal principal) {
        Customer currentCustomer = customerDAO.findByPersonInfo_Username(principal.getName()).orElse(null);
        wishList = wishListDAO.findByCustomer(currentCustomer).orElse(null);
        if (wishList == null) {
            wishList = new WishList();
            wishList.setCustomer(currentCustomer);
            wishListDAO.save(wishList);
        }
    }

    @GetMapping("/removeFromWishList")
    @Transactional
    public String removeFromWishList(@RequestParam Long itemId) {
        wishList = wishListDAO.findById(wishList.getWishListId()).orElse(null);
        wishList.getListDetails().removeIf(det -> det.getItemId().equals(itemId));
        return "redirect:wishlist.html";
    }

    @GetMapping("/wishlist.html")
    public String wishListPage(
            @RequestParam(name = "categories", required = false, defaultValue = "0") Long cateId,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            Principal principal,
            Model model) {
        initWishList(principal);
        List<WishListDetail> listDetails;
        if (cateId == 0) {
            listDetails = wishList.getListDetails();
        } else {
            listDetails = wishList.getListDetails().stream()
                    .filter(det -> det.getItem().getCategoryId().equals(cateId))
                    .collect(Collectors.toList());
        }
        page = Math.min(Math.max(page, 1), (listDetails.size() + 4) / 5);
        if (page > 0) {
            model.addAttribute("listDetails", CustomCollectionUtils.getPage(listDetails, Math.toIntExact(page), 5));
        }
        model.addAttribute("summaryText",
                String.format("Hiển thị từ %d đến %d trên tổng số %d sản phẩm",
                        (page - 1) * 5 + 1,
                        Math.min((page - 1) * 5 + 5, listDetails.size()),
                        listDetails.size()));
        model.addAttribute("currentPage", page);
        model.addAttribute("currentCate", cateId);
        model.addAttribute("lastPage", (listDetails.size() + 4) / 5);
        return "wishlist";
    }
}
