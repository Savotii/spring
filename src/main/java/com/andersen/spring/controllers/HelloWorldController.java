package com.andersen.spring.controllers;

import com.andersen.spring.controllers.exceptions.NotFoundException;
import com.andersen.spring.entity.User;
import com.andersen.spring.facade.MarketFacade;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    protected String main(Model model, HttpSession session) {
        model.addAttribute("msg", "hello world");

        return "index";
    }

    @RequestMapping(value = "/GetExceptionHandler")
    protected String getExceptionHandler(Model model) throws Exception {
        throw new NotFoundException("Собственный обработчик ошибки.");
    }

    @RequestMapping(value = "/ProductList")
    protected String productList(Model model) {
        model.addAttribute("productList", marketFacade.getAllProducts());
        return "productList";
    }

    @RequestMapping(value = "/AccountList")
    protected String accountList(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null)
            model.addAttribute("accountList", marketFacade.getAll(user.getId()));

        return "accountList";
    }

    @RequestMapping(value = "/index")
    protected String index(Model model) {
        model.addAttribute("msg", "going to index");
        return "index";
    }

    @RequestMapping(value = "/logout")
    protected String logout(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        model.addAttribute("logout", true);
        //session.removeAttribute("user");
        return "index";
    }

    @RequestMapping(value = "/login")
    protected String login(Model model, HttpServletRequest request, HttpSession session) {
        String login = (String) request.getParameter("login");
        String email = (String) request.getParameter("email");
       SecurityContext securityContext = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication = securityContext.getAuthentication();
        UserDetails principal = null;
        if(!(authentication instanceof AnonymousAuthenticationToken))
        {
            principal = (UserDetails)authentication.getPrincipal();
            email = principal.getUsername();
        }

        User user = marketFacade.getUserByEmail(email);
        model.addAttribute("user", user);
        if (user == null) {
            model.addAttribute("userNotFound", "Авторизация пользователя не выполнена.<br /> Попробуйте еще раз.");
        }
        if (user != null)
            session.setAttribute("user", user);

        return "index";
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