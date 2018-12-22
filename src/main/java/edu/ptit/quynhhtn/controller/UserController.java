package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.config.SiteConfig;
import edu.ptit.quynhhtn.dao.CustomerDAO;
import edu.ptit.quynhhtn.dao.PersonDAO;
import edu.ptit.quynhhtn.entity.Customer;
import edu.ptit.quynhhtn.form.ProfileFrm;
import edu.ptit.quynhhtn.form.RegisterFrm;
import edu.ptit.quynhhtn.service.UserProfileService;
import edu.ptit.quynhhtn.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    PersonDAO personDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    UserProfileService userProfileService;

    @GetMapping("/profile.html")
    public String userProfile(@ModelAttribute ProfileFrm profileFrm, Principal principal, Model model){
        if(principal == null) return "register-login";
        Customer currentCustomer = customerDAO.findByPersonInfo_Username(principal.getName()).orElse(null);
        profileFrm.setDob(DateUtils.formatDate(currentCustomer.getPersonInfo().getDob()));
        profileFrm.setEmail(currentCustomer.getPersonInfo().getEmail());
        profileFrm.setFullName(currentCustomer.getPersonInfo().getFullName());
        profileFrm.setShortName(currentCustomer.getPersonInfo().getShortName());
        profileFrm.setPhone(currentCustomer.getPersonInfo().getPhone());
        model.addAttribute("profileFrm",profileFrm);
        return "profile";
    }

    @PostMapping("/doUpdateProfile")
    public String updateProfile(@ModelAttribute ProfileFrm profileFrm, Principal principal, Model model){
        if(principal == null) return "register-login";
        userProfileService.updateProfile(profileFrm, principal.getName());
        return "redirect:profile.html";
    }

    @GetMapping("/register-login.html")
    public String registerLogin(
            @RequestParam(name = "error", required =  false, defaultValue = "false") boolean loginResult,
            @RequestParam(name = "registerError", required =  false, defaultValue = "false") boolean registerResult,
            RegisterFrm registerFrm,
            Principal principal, Model model){
        model.addAttribute("registerFrm", registerFrm);
        if(principal != null) {
            SiteConfig.getInstance().setLoggedInUser(personDAO.findPersonByUsername(principal.getName()).orElse(null));
            return "redirect:index.html";
        }
        else{
            model.addAttribute("registerFrm", registerFrm);
            return "register-login";
        }
    }

    @PostMapping("/doRegister")
    public String register(RegisterFrm registerFrm){
        if(personDAO.findPersonByEmail(registerFrm.getEmail()).orElse(null) == null){
            userProfileService.addNewUser(registerFrm);

            return "redirect:register-login.html?registerError=false";
        }
        else{
            return "redirect:register-login.html?registerError=true";
        }
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        return "redirect:index.html";
    }

}
