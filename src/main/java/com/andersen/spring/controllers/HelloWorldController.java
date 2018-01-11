package com.andersen.spring.controllers;

import com.andersen.spring.controllers.exceptions.NotFoundException;
import com.andersen.spring.entity.User;
import com.andersen.spring.facade.MarketFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class HelloWorldController {

    @Autowired
    private MarketFacade marketFacade;

    @RequestMapping(value = "/")
    protected String main(Model model)
    {
        model.addAttribute("msg", "hello world");

        return "index";
    }

    @RequestMapping(value = "/GetExceptionHandler")
    protected String getExceptionHandler(Model model) throws Exception
    {
        throw new NotFoundException("Собственный обработчик ошибки.");
    }

    @RequestMapping(value = "/ProductList")
    protected String productList(Model model)
    {
        model.addAttribute("productList", marketFacade.getAllProducts());
        return "productList";
    }

    @RequestMapping(value = "/AccountList")
    protected String accountList(Model model, HttpSession httpSession){

        User user = (User) httpSession.getAttribute("user");
        if (user != null)
            model.addAttribute("accountList", marketFacade.getAll(user.getId()));

        return "accountList";
    }

    @RequestMapping(value = "/index")
    protected String index(Model model)
    {
        model.addAttribute("msg", "going to index");
        return "index";
    }

    @RequestMapping(value = "/logout")
    protected String logout(Model model)
    {
        model.addAttribute("logout", true);
        return "index";
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

    @ExceptionHandler(NotFoundException.class)
    public String handlerNoFoundException(HttpServletRequest request, Exception ex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("errorPage");
        return "errorPage";

    }

}