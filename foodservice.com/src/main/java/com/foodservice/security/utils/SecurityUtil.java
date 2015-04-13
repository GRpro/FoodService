package com.foodservice.security.utils;

import com.foodservice.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

public class SecurityUtil {

    public static boolean isAuthenticated() {
        boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomUserDetails;
        return isAuthenticated;
    }

    /**
     * eturns null if user is not authenticated at the
     * moment of invoking this method
     * @return
     */
    public static CustomUserDetails getUserDetails() {
        if (isAuthenticated()) {
            CustomUserDetails customUserDetails = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            return customUserDetails;
        }
        return null;
    }

}
