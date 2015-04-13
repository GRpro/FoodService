package com.foodservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicController {

    @Value("${webservice.url.root}")
    private String webserviceRootUrl;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("public/index");
        return modelAndView;
    }

    @RequestMapping(value = {"/service"}, method = RequestMethod.GET)
    public ModelAndView service() {
        ModelAndView modelAndView = new ModelAndView("public/service");
        modelAndView.addObject("webServiceRootUrl", webserviceRootUrl);
        return modelAndView;
    }


    @RequestMapping(value = {"/social"}, method = RequestMethod.GET)
    public ModelAndView goToPersonProfile() {
        ModelAndView modelAndView = new ModelAndView("public/social");
        modelAndView.addObject("webServiceRootUrl", webserviceRootUrl);
        return modelAndView;
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = {"/user/friends"}, method = RequestMethod.GET)
    public ModelAndView goToFriendsPage() {
        ModelAndView modelAndView = new ModelAndView("private/ROLE_USER/friends");
        modelAndView.addObject("webServiceRootUrl", webserviceRootUrl);
        return modelAndView;
    }

    //*************************Sign up forms***************************//


    @RequestMapping(value = "/form/manager", method = RequestMethod.GET)
    public ModelAndView managerSignUpForm() {
        ModelAndView modelAndView = new ModelAndView("public/register_manager");
        return modelAndView;
    }

    @RequestMapping(value = "/form/shopadmin", method = RequestMethod.GET)
    public ModelAndView shopAdminSignUpForm() {
        ModelAndView modelAndView = new ModelAndView("public/register_admin");
        return modelAndView;
    }

    @RequestMapping(value = "/form/simpleuser", method = RequestMethod.GET)
    public ModelAndView simpleUserSignUpForm() {
        ModelAndView modelAndView = new ModelAndView("public/register_user");
        return modelAndView;
    }


    @RequestMapping(value = "/form/shop", method = RequestMethod.GET)
    public ModelAndView shopRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("private/ROLE_SHOP_ADMIN/add_shop");
        return modelAndView;
    }



}
