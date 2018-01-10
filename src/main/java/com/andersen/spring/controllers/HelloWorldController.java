package com.andersen.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {


    @RequestMapping(value = "/")
    protected ModelAndView main(){

        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("msg", "hello world");

        return model;
    }

    @RequestMapping(value = "/start")
    protected ModelAndView start()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "It's starting");
        modelAndView.setViewName("HelloWorldPage");
        return modelAndView;
    }

    @RequestMapping(value = "/view")
    protected ModelAndView view()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "It's viewing");
        modelAndView.setViewName("HelloWorldPage");
        return modelAndView;
    }
}