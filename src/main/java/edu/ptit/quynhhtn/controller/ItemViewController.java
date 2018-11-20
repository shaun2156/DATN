package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.dao.CommentDAO;
import edu.ptit.quynhhtn.dao.ItemDAO;
import edu.ptit.quynhhtn.entity.Comment;
import edu.ptit.quynhhtn.entity.Item;
import edu.ptit.quynhhtn.form.ItemCommentFrm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ItemViewController {
    @Autowired
    ItemDAO itemDAO;

    @Autowired
    CommentDAO commentDAO;

    @GetMapping("/full-view.html")
    public String addToCart(@ModelAttribute ItemCommentFrm itemCommentFrm,
            @RequestParam Long item, Model model){
        Item selected = itemDAO.findById(item).orElse(null);
        model.addAttribute("currentItem", selected);
        model.addAttribute("itemCommentFrm", itemCommentFrm);
        model.addAttribute("relatedItems", itemDAO.findRandom3ItemByCategory(selected.getCategoryId()));
        return "full-view";
    }

    @PostMapping("/submitComment")
    public String submitTicket(@ModelAttribute ItemCommentFrm itemCommentFrm){
        Comment comment = new Comment();
        comment.setItemId(itemCommentFrm.getItemId());
        comment.setDisplayName(itemCommentFrm.getFullName());
        if(itemCommentFrm.getRating() == null) {
            comment.setRating(5d);
        }
        else{
            comment.setRating(itemCommentFrm.getRating());
        }
        comment.setSummary(itemCommentFrm.getSummary());
        comment.setContent(itemCommentFrm.getContent());
        commentDAO.save(comment);
        return "redirect:full-view.html?item=" + itemCommentFrm.getItemId();
    }
}
