package com.foodservice.security;

import com.foodservice.entities.data.SystemStatus;
import com.foodservice.services.UserCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private UserCommonService userCommonService;

    @Autowired
    public void setUserCommonService(UserCommonService userCommonService) {
        this.userCommonService = userCommonService;
    }

    @Override
    public void onLogoutSuccess
            (HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        userCommonService.changeSystemStatus(((CustomUserDetails)authentication.getPrincipal()).getId(), SystemStatus.OFFLINE);
        super.onLogoutSuccess(request, response, authentication);
    }
}