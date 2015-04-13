package com.foodservice.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class SecurityController {

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        ModelAndView modelAndView = null;
        if (hasRole("ROLE_USER")) {
            System.out.println("Has role ROLE_USER");
            modelAndView = new ModelAndView("redirect:/profile/user");
        }
        if (hasRole("ROLE_SHOP_ADMIN")) {
            System.out.println("Has role ROLE_SHOP_ADMIN");
            modelAndView = new ModelAndView("redirect:/profile/admin");
        }
        if (hasRole("ROLE_MANAGER")) {
            System.out.println("Has role ROLE_MANAGER");
            modelAndView = new ModelAndView("private/ROLE_MANAGER/profile");
        }
        return modelAndView;
    }

    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }
}
