package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.dao.CategoryDAO;
import edu.ptit.quynhhtn.dao.ItemDAO;
import edu.ptit.quynhhtn.dao.SearchHistoryDAO;
import edu.ptit.quynhhtn.entity.Category;
import edu.ptit.quynhhtn.entity.Item;
import edu.ptit.quynhhtn.entity.SearchHistory;
import edu.ptit.quynhhtn.service.UserProfileService;
import edu.ptit.quynhhtn.utils.CustomCollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    ItemDAO itemDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    SearchHistoryDAO searchHistoryDAO;

    @GetMapping("/search.html")
    public String shopView(
            @RequestParam(name = "categories", required = false, defaultValue = "0") Long categories,
            @RequestParam String search,
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            Principal principal,
            Model model) {
        if (principal != null) {
            //Store User search query
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setCustomer(UserProfileService.getInstance().currentCustomer(principal));
            searchHistory.setContext(categories + "");
            searchHistory.setQuery(search);
            searchHistoryDAO.save(searchHistory);
        }
        List<Item> searchResult;
        int pageSize = 10;
        int lastPage = 0;
        int totalItems = 0;

        if(page == 0) {
            page = 1l;
        }
        if (categories != 0) {
            searchResult = itemDAO.findAllByCategoryIdAndQuery(categories, search);
            lastPage = (searchResult.size() + pageSize - 1) / pageSize;
            totalItems = searchResult.size();
        } else {
            Page<Item> itemPage = itemDAO.findAllByNameContaining(search, PageRequest.of(Math.toIntExact(page) - 1, pageSize));
            totalItems = (int) itemPage.getTotalElements();
            lastPage = itemPage.getTotalPages();
            if(page > lastPage){
                itemPage = itemDAO.findAllByNameContaining(search, PageRequest.of(lastPage, pageSize));
            }
            searchResult = itemPage.getContent();
            model.addAttribute("listItems", searchResult);
        }
        if (!CustomCollectionUtils.isEmpty(searchResult) && categories != 0) {
            model.addAttribute("listItems", CustomCollectionUtils.getPage(searchResult, Math.toIntExact(page), pageSize));
        }
        if(page > lastPage){
            page = (long)lastPage;
        }
        model.addAttribute("currentCate", categories);
        model.addAttribute("searchQuery", search);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("summaryText",
                String.format("Hiển thị từ %d đến %d trên tổng số %d sản phẩm",
                        (page - 1) * pageSize + 1,
                        Math.min((page - 1) * pageSize + pageSize, totalItems),
                        totalItems));
        return "search";
    }
}
