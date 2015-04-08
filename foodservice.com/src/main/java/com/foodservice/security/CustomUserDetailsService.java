package com.foodservice.security;

import com.foodservice.entities.user.ManagerUser;
import com.foodservice.entities.user.ShopAdminUser;
import com.foodservice.entities.user.SimpleUser;
import com.foodservice.entities.user.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.ws.rs.core.MediaType;
import java.util.List;


public class CustomUserDetailsService implements UserDetailsService {


    @Value("${webservice.url.root}")
    private String webserviceRootUrl;

    private Client client;

    public CustomUserDetailsService() {
        this.client = Client.create();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();

        WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/manager/byEmail");
        ClientResponse response = webResource
                .queryParam("email", email)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        User user;
        if ((user = response.getEntity(ManagerUser.class)) != null) {
            auths.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        } else {
            webResource = client.resource(webserviceRootUrl + "/resources/users/simple/byEmail");
            response = webResource
                    .queryParam("email", email)
                    .type(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            if ((user = response.getEntity(SimpleUser.class)) != null) {
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            } else {
                webResource = client.resource(webserviceRootUrl + "/resources/users/admin/byEmail");
                response = webResource
                        .queryParam("email", email)
                        .type(MediaType.APPLICATION_JSON)
                        .get(ClientResponse.class);
                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
                }
                if ((user = response.getEntity(ShopAdminUser.class)) != null) {
                    auths.add(new SimpleGrantedAuthority("ROLE_SHOP_ADMIN"));
                }
            }
        }
        return new CustomUserDetails(auths, user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserType());
    }
}