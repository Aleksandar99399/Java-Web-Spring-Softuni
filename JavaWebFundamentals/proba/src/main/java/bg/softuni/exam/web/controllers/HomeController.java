package bg.softuni.exam.web.controllers;


import bg.softuni.exam.models.enities.NameType;
import bg.softuni.exam.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private ProductService productService;
    private ModelMapper modelMapper;

    @Autowired
    public HomeController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session){
        if(session.getAttribute("username")==null){
            return "redirect:/index";
        }
        model.addAttribute("sum",this.productService.getProductSum());
        model.addAttribute("foods",this.productService.getProductByType(NameType.FOOD));
        model.addAttribute("drinks",this.productService.getProductByType(NameType.DRINK));
        model.addAttribute("households",this.productService.getProductByType(NameType.HOUSEHOLD));
        model.addAttribute("other",this.productService.getProductByType(NameType.OTHER));
        return "home";
    }
}
