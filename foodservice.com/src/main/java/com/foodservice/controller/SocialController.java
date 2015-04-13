package com.foodservice.controller;

import com.foodservice.entities.friendship.Friendship;
import com.foodservice.entities.user.ManagerUser;
import com.foodservice.entities.user.ShopAdminUser;
import com.foodservice.entities.user.SimpleUser;
import com.foodservice.security.CustomUserDetails;
import com.foodservice.security.utils.SecurityUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
public class SocialController {

    @Value("${webservice.url.root}")
    private String webserviceRootUrl;


    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception e) {
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView("public/result/on_result");
        modelAndView.addObject("title", "error");
        modelAndView.addObject("message", "something gone wrong!");
        return modelAndView;
    }


    //**********************************profiles**************************//



    @RequestMapping(value = "/visit/user/{id}", method = RequestMethod.GET)
    public ModelAndView visitSimpleUserProfile(@PathVariable("id") Integer userId) {

        Client client = Client.create();
        WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/simple/byId");
        ClientResponse response = webResource
                .queryParam("id", String.valueOf(userId))
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        SimpleUser simpleUser = response.getEntity(SimpleUser.class);

        ModelAndView modelAndView = new ModelAndView("public/simple_user_profile");
        modelAndView.addObject("webServiceRootUrl", webserviceRootUrl);

        boolean isAuthenticated = SecurityUtil.isAuthenticated();
        modelAndView.addObject("isAuthenticated", isAuthenticated);
        if (isAuthenticated) {
            //if page is the page of current authenticated user
            CustomUserDetails userDetails = SecurityUtil.getUserDetails();
            if (userDetails.getId() == userId) {
                return new ModelAndView("redirect:/profile/user");
            }

            modelAndView.addObject("userDetails", SecurityUtil.getUserDetails());

            webResource = client.resource(webserviceRootUrl + "/resources/friendship/byCouple");
            response = webResource
                    .queryParam("user1Id", String.valueOf(userId))
                    .queryParam("user2Id", String.valueOf(SecurityUtil.getUserDetails().getId()))
                    .type(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            Friendship friendship = response.getEntity(Friendship.class);
            modelAndView.addObject("friendship", friendship);
        }
        modelAndView.addObject("simpleUser", simpleUser);
        modelAndView.addObject("relativeUserId", userId);

        return modelAndView;
    }





    @RequestMapping(value = "/visit/manager/{id}", method = RequestMethod.GET)
    public ModelAndView visitManagerUserProfile(@PathVariable("id") Integer userId) {

        Client client = Client.create();
        WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/manager/byId");
        ClientResponse response = webResource
                .queryParam("id", String.valueOf(userId))
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        ManagerUser managerUser = response.getEntity(ManagerUser.class);
        ModelAndView modelAndView = new ModelAndView("public/manager_user_profile");
        modelAndView.addObject("managerUser", managerUser);
        modelAndView.addObject("webServiceRootUrl", webserviceRootUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/visit/shopAdmin/{id}", method = RequestMethod.GET)
    public ModelAndView visitShopAdminUserProfile(@PathVariable("id") Integer userId) {

        Client client = Client.create();
        WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/admin/byId");
        ClientResponse response = webResource
                .queryParam("id", String.valueOf(userId))
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        ShopAdminUser shopAdminUser = response.getEntity(ShopAdminUser.class);
        ModelAndView modelAndView = new ModelAndView("public/shop_admin_user_profile");
        modelAndView.addObject("shopAdminUser", shopAdminUser);
        modelAndView.addObject("webServiceRootUrl", webserviceRootUrl);

        return modelAndView;
    }


}
