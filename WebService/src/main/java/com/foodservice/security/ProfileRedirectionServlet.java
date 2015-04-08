package com.foodservice.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/view/private/redirect")
public class ProfileRedirectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (hasRole("ROLE_USER")) {
            System.out.println("Has role ROLE_USER");
            req.getRequestDispatcher("/view/private/ROLE_USER/profile.jsp").forward(req,resp);
        }
        if (hasRole("ROLE_SHOP_ADMIN")) {
            System.out.println("Has role ROLE_SHOP_ADMIN");
            req.getRequestDispatcher("/view/private/ROLE_SHOP_ADMIN/profile.jsp").forward(req,resp);
        }
        if (hasRole("ROLE_MANAGER")) {
            System.out.println("Has role ROLE_MANAGER");
            req.getRequestDispatcher("/view/private/ROLE_MANAGER/profile.jsp").forward(req,resp);
        }
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
