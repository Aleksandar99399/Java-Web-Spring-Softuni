package bg.softuni.exam.web.controllers;

import bg.softuni.exam.models.binding.ProductRegisterBindingModel;
import bg.softuni.exam.models.services.ProductServiceModel;
import bg.softuni.exam.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String productAdd(Model model, HttpSession session){
        if(session.getAttribute("username")==null){
            return "redirect:/index";
        }
        model.addAttribute("product",new ProductRegisterBindingModel());
        return "product-add";
    }


    @PostMapping("/add")
    public String postProductAdd(@Valid @ModelAttribute("product")ProductRegisterBindingModel productRegisterBindingModel,
                                 BindingResult result){

        if(!this.productService.isProductNameFree(productRegisterBindingModel.getName())){
           result.rejectValue("name","name","The name is already in the database!");
        }
        if(result.hasErrors()){
            return "product-add";

        }
        this.productService.register(this.modelMapper.map(productRegisterBindingModel, ProductServiceModel.class));
        return "redirect:/home";

    }

    @GetMapping("/buy/{id}")
    public String buyOne(@PathVariable("id") String id,HttpSession session){
        if(session.getAttribute("username")==null){
            return "redirect:/index";
        }

        this.productService.buyOne(id);
        return "redirect:/home";
    }

    @GetMapping("/buy/all")
    public String buyAll(HttpSession session){
        if(session.getAttribute("username")==null){
            return "redirect:/index";
        }
        this.productService.buyAll();
        return "redirect:/home";
    }
}
