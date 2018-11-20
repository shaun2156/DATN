package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.dao.ItemDAO;
import edu.ptit.quynhhtn.entity.Category;
import edu.ptit.quynhhtn.entity.TestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonController {
    @Autowired
    ItemDAO itemDAO;

    @GetMapping("/{staticPath}")
    public String defaultStaticHandler(@PathVariable String staticPath){
//        return "coming-soon";
        return staticPath.split("\\.")[0];
    }

    @GetMapping(path = {"/index.html","/"})
    public String indexHandler(Model model){
        model.addAttribute("newProducts",itemDAO.findTop10ByOrderByCreatedDateDesc());
        return "index";
    }
}
