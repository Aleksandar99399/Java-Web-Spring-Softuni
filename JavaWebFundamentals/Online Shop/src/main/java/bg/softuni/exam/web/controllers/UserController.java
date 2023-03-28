package bg.softuni.exam.web.controllers;

import bg.softuni.exam.models.binding.UserLoginBindingModel;
import bg.softuni.exam.models.binding.UserRegisterBindingModel;
import bg.softuni.exam.models.services.UserServiceModel;
import bg.softuni.exam.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String register(Model model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/home";
        }
        model.addAttribute("user", new UserRegisterBindingModel());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("user") UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "confirmPassword", "The passwords are not equal!");
        }
        if (!this.userService.isUsernameFree(userRegisterBindingModel.getUsername())) {
            bindingResult.rejectValue("username", "username", "The username is not free!");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }
        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model,HttpSession session) {
        if(session.getAttribute("username")!=null){
            return "redirect:/home";
        }
        model.addAttribute("user", new UserLoginBindingModel());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute("user") UserLoginBindingModel userLoginBindingModel,
                            BindingResult result, HttpSession session) {

        if (!userService.areValidLoginCredentials(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword())) {
            result.rejectValue("username", "username", "The username or password are incorrect!");
        }
        if (result.hasErrors()) {
            return "login";
        }

        session.setAttribute("username", userLoginBindingModel.getUsername());

        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}
