package bg.softuni.exam.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {


    @GetMapping("/index")
    public String index(HttpSession session){
        if(session.getAttribute("username")!=null){
            return "redirect:/home";
        }
        return "index";
    }
}
