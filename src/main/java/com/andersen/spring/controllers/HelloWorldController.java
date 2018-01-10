package com.andersen.spring.controllers;

import com.andersen.spring.facade.MarketFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

    @Autowired
    private MarketFacade marketFacade;

    @RequestMapping(value = "/")
    protected ModelAndView main(){

        ModelAndView model = new ModelAndView("index");
        model.addObject("msg", "hello world");

        return model;
    }

    @RequestMapping(value = "/ProductList")
    protected ModelAndView productList()
    {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList", marketFacade.getAllProducts());
        modelAndView.setViewName("productList");
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

    @RequestMapping(value = "/index")
    protected ModelAndView index(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "going to index.jsp");
        modelAndView.setViewName("index");
        return modelAndView;

    }
}