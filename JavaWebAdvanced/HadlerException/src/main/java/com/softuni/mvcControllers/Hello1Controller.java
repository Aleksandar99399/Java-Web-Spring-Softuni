package com.softuni.mvcControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Hello1Controller {
    @GetMapping("/crash1")
    public ModelAndView crashMe(){
        throw new HelloException("I crashed");
    }
}
