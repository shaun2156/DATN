package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.dao.CategoryDAO;
import edu.ptit.quynhhtn.dao.ItemDAO;
import edu.ptit.quynhhtn.entity.Category;
import edu.ptit.quynhhtn.entity.Item;
import edu.ptit.quynhhtn.utils.CustomCollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CategoriesController {
    @Autowired
    ItemDAO itemDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @GetMapping("/shop.html")
    public String shopView(
            @RequestParam Long categories,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            Model model){
        int pageSize = 10;
        Category currentCate = categoryDAO.findById(categories).orElse(null);
        page = Math.min(Math.max(page, 1), (currentCate.getListItems().size() + pageSize -1) / pageSize);
        if (page > 0) {
            model.addAttribute("listItems", CustomCollectionUtils.getPage(currentCate.getListItems(), Math.toIntExact(page), pageSize));
        }
        model.addAttribute("currentCate",currentCate);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", (currentCate.getListItems().size() + pageSize - 1) / pageSize);
        model.addAttribute("summaryText",
                String.format("Hiển thị từ %d đến %d trên tổng số %d sản phẩm",
                        (page - 1) * pageSize + 1,
                        Math.min((page - 1) * pageSize + pageSize, currentCate.getListItems().size()),
                        currentCate.getListItems().size()));
        return "shop";
    }
}
