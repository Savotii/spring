package com.andersen.spring.controllers;

import com.andersen.spring.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    //Попадаем сюда при на старте приложения.
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userJSP", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/check-user")
    public ModelAndView checkUser(@ModelAttribute("userJSP")User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("secondPage");
        modelAndView.addObject("userJSP", user);
        return modelAndView;
    }
}
