package com.foodservice.security;

import com.foodservice.entities.data.SystemStatus;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {


    @Value("${webservice.url.root}")
    private String webserviceRootUrl;

    @Override
    public void onLogoutSuccess
            (HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        setOfflineSystemStatus(((CustomUserDetails)authentication.getPrincipal()).getId());
        super.onLogoutSuccess(request, response, authentication);
    }


    private void setOfflineSystemStatus(int id) {
        Client client = Client.create();
        WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/statuses");
        ClientResponse response = webResource
                .queryParam("id", String.valueOf(id))
                .queryParam("status", SystemStatus.OFFLINE.toString())
                .put(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
    }
}