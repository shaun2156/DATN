package edu.ptit.quynhhtn.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class SiteErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleErrorByCode(HttpServletRequest request, Model model){
        System.out.println(request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        model.addAttribute("error_msg","Có lỗi xảy ra! Vui lòng thử lại sau.");
        return "errorPage";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
