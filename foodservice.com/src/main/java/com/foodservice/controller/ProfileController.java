package com.foodservice.controller;

import com.foodservice.entities.Shop;
import com.foodservice.entities.user.ManagerUser;
import com.foodservice.entities.user.ShopAdminUser;
import com.foodservice.entities.user.SimpleUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    @Value("${webservice.url.root}")
    private String webserviceRootUrl;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView shopAdminProfile(Principal principal) {
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("private/ROLE_SHOP_ADMIN/profile");

            //load shopAdminUser
            Client client = Client.create();
            WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/admin/byEmail");
            ClientResponse response = webResource
                    .queryParam("email", String.valueOf(principal.getName()))
                    .type(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            ShopAdminUser shopAdminUser = response.getEntity(ShopAdminUser.class);
            modelAndView.addObject("user", shopAdminUser);

            //load managers
            webResource = client.resource(webserviceRootUrl + "/resources/users/manager/byShopAdminID");
            response = webResource
                    .queryParam("shopAdminID", String.valueOf(shopAdminUser.getId()))
                    .type(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            List<ManagerUser> managerUsers = response.getEntity(new GenericType<List<ManagerUser>>(){});
            modelAndView.addObject("managers", managerUsers);

            //load shops
            webResource = client.resource(webserviceRootUrl + "/resources/shops/byShopAdminID");
            response = webResource
                    .queryParam("shopAdminUserID", String.valueOf(shopAdminUser.getId()))
                    .type(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            List<Shop> shops = response.getEntity(new GenericType<List<Shop>>(){});
            modelAndView.addObject("shops", shops);

            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "error");
            modelAndView.addObject("message", "page cannot be loaded!");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView simpleUserProfile(Principal principal) {
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("private/ROLE_USER/profile");

            //load simpleUser
            Client client = Client.create();
            WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/simple/byEmail");
            ClientResponse response = webResource
                    .queryParam("email", String.valueOf(principal.getName()))
                    .type(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            SimpleUser simpleUser = response.getEntity(SimpleUser.class);
            modelAndView.addObject("user", simpleUser);


            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "error");
            modelAndView.addObject("message", "page cannot be loaded!");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public ModelAndView managerUserProfile(Principal principal) {
        return null;
    }

}
