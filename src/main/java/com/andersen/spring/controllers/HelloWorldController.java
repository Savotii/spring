package com.andersen.spring.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class HelloWorldController extends AbstractController {


    @RequestMapping(value = "/")
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

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