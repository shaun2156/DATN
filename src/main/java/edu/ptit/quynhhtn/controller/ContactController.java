package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.dao.SupportTicketDAO;
import edu.ptit.quynhhtn.entity.SupportTicket;
import edu.ptit.quynhhtn.form.SupportTicketFrm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {
    @Autowired
    SupportTicketDAO supportTicketDAO;

    @GetMapping("/contact.html")
    public String contactPage(@ModelAttribute SupportTicketFrm supportTicketFrm,
                              @RequestParam(name = "submitResult", required = false, defaultValue = "") String submitResult,
                              Model model){
        model.addAttribute("supportTicketFrm", supportTicketFrm);
        model.addAttribute("submit",submitResult);
        return "contact";
    }

    @PostMapping("/submitSupportTicket")
    public String submitTicket(@ModelAttribute SupportTicketFrm supportTicketFrm, RedirectAttributes redirectAttributes){
        SupportTicket supportTicket = new SupportTicket();
        supportTicket.setFullName(supportTicketFrm.getFullName());
        supportTicket.setEmail(supportTicketFrm.getEmail());
        supportTicket.setContent(supportTicketFrm.getContent());
        supportTicketDAO.save(supportTicket);
        redirectAttributes.addAttribute("submitResult","success");
        return "redirect:contact.html";
    }
}
