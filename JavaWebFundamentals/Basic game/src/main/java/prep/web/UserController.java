package prep.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import prep.models.binding.UserLoginBindingModel;
import prep.models.binding.UserRegisterBindingModel;
import prep.models.service.UserServiceModel;
import prep.servicies.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model){
        if (!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel",new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                        UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes,
                                        ModelAndView modelAndView){

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){

            modelAndView.addObject("userRegisterBindingModel",userRegisterBindingModel);

            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);

            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            return "redirect:register";
        }else {
            this.userService.register(this.modelMapper
            .map(userRegisterBindingModel, UserServiceModel.class));

            return "login";
        }

    }

    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel",new UserLoginBindingModel());
            model.addAttribute("notFound",false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBindingModel")
                                                 UserLoginBindingModel userLoginBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     ModelAndView modelAndView,
                                     HttpSession httpSession){

        if (bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userLoginBindingModel",bindingResult);

            return "redirect:login";
        }else {
            UserServiceModel user=
                    this.userService.findByUsername(userLoginBindingModel.getUsername());

            if (user==null || !user.getPassword().equals(userLoginBindingModel.getPassword())){
                redirectAttributes.addFlashAttribute("notFound",true);
                redirectAttributes.addFlashAttribute
                        ("org.springframework.validation.BindingResult.userLoginBindingModel",bindingResult);
                return "redirect:login";
            }else {
                httpSession.setAttribute("user", user);
            }
            return "redirect:/";
        }


    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
}
