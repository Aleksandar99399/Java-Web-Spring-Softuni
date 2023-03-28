package com.softuni.mvcControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/crash")
    public ModelAndView crashMe(){
        throw new HelloException("I crashed");
    }

    @GetMapping("crash-bad")
    public ModelAndView crashe(){
        throw new RuntimeException("I crashed baby");
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({HelloException.class})
//    public ModelAndView handleHelloException(HelloException ex){
//        ModelAndView modelAndView=new ModelAndView("errorhandler");
//        modelAndView.addObject("message", ex.getMessage());
//
//        return modelAndView;
//    }
}
