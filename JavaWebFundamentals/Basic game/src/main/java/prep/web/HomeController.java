package prep.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import prep.servicies.HeroService;
import prep.servicies.UserService;

import javax.servlet.http.HttpSession;

@Controller

public class HomeController {

    private final UserService userService;
    private final HeroService heroService;

    @Autowired
    public HomeController(UserService userService, HeroService heroService) {
        this.userService = userService;
        this.heroService = heroService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView){
        if (httpSession.getAttribute("user")==null){
            modelAndView.setViewName("index");
        }else {
            modelAndView.addObject("heroes",this.heroService.findAllHeroes());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}
