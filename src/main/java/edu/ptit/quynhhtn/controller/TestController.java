package edu.ptit.quynhhtn.controller;

import edu.ptit.quynhhtn.service.TestDBInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @Autowired
    TestDBInitService testService;

    @GetMapping("/initTestData")
    public String initTest(){
        testService.testDataInitialization();
        return "redirect:index.html";
    }
}
