package com.andersen.spring.controllers;

import com.andersen.spring.entity.User;
import com.andersen.spring.facade.MarketFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloWorldController {

    @Autowired
    private MarketFacade marketFacade;

    @RequestMapping(value = "/")
    protected ModelAndView main(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("index");
        model.addObject("msg", "hello world");

        return model;
    }

    @RequestMapping(value = "/ProductList")
    protected ModelAndView productList(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList", marketFacade.getAllProducts());
        modelAndView.setViewName("productList");
        return modelAndView;
    }

    @RequestMapping(value = "/AccountList")
    protected ModelAndView accountList(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null)
            modelAndView.addObject("accountList", marketFacade.getAll(user.getId()));
        modelAndView.setViewName("accountList");
        return modelAndView;
    }

    @RequestMapping(value = "/view")
    protected ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "It's viewing");
        modelAndView.setViewName("HelloWorldPage");
        return modelAndView;
    }

    @RequestMapping(value = "/index")
    protected ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "going to index.jsp");
        modelAndView.setViewName("index");
        return modelAndView;

    }


    @RequestMapping(value = "/logout")
    protected ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("logout", true);
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    protected ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("index");
        String login = (String) request.getParameter("login");
        String email = (String) request.getParameter("email");
        User user = marketFacade.getUserByEmail(email);
        modelAndView.addObject("user", user);
        if (user == null) {
            modelAndView.addObject("userNotFound", "Авторизация пользователя не выполнена.<br /> Попробуйте еще раз.");
        }
        if (user != null)
            request.getSession().setAttribute("user", user);

        return modelAndView;
    }
}