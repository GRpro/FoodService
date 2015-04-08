package com.foodservice.security;

import com.foodservice.entities.data.SystemStatus;
import com.foodservice.services.UserCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private UserCommonService userCommonService;

    @Autowired
    public void setUserCommonService(UserCommonService userCommonService) {
        this.userCommonService = userCommonService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setOnlineSystemStatus(((CustomUserDetails)authentication.getPrincipal()).getId());
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private void setOnlineSystemStatus(int id) {
        userCommonService.changeSystemStatus(id, SystemStatus.ONLINE);
        System.out.println("***STATUS ONLINE***");
    }
}
