package com.foodservice.controller;

import com.foodservice.entities.Photo;
import com.foodservice.entities.Shop;
import com.foodservice.entities.user.ManagerUser;
import com.foodservice.entities.user.ShopAdminUser;
import com.foodservice.entities.user.SimpleUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

    @Value("${webservice.url.root}")
    private String webserviceRootUrl;

    private Client client;

    public RegistrationController() {
        this.client = Client.create();
    }






    //***********************************Users' registration************************//


    @RequestMapping(value = "/manager", method = RequestMethod.POST)
    public ModelAndView registerManager(@ModelAttribute("manager") ManagerUser managerUser,
                                        @RequestParam("userAvatar") MultipartFile userAvatar,
                                        @RequestParam String shopAdminUserEmail,
                                        @RequestParam("dobDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dobDate) {
        ModelAndView modelAndView;
        try {

            managerUser.setDob(dobDate);

            if (userAvatar.getSize() != 0) {
                Photo photo = new Photo();
                photo.setImage(userAvatar.getBytes());
                photo.setName(managerUser.getEmail() + " avatar");
                managerUser.setPhotoId(createPhoto(photo));
            }
            WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/manager");
            ClientResponse response = webResource
                    .queryParam("shopAdminUserEmail", shopAdminUserEmail)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(managerUser)
                    .post(ClientResponse.class, managerUser);
            if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "success");
            modelAndView.addObject("message", "new Manager was successfully registered");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "error");
            modelAndView.addObject("message", "registration failed!");
            return modelAndView;
        }

    }

    @RequestMapping(value = "/simpleUser", method = RequestMethod.POST)
    public ModelAndView registerSimpleUser(@ModelAttribute SimpleUser simpleUser,
                                           @RequestParam(value = "userAvatar") MultipartFile userAvatar,
                                           @RequestParam("dobDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dobDate) {
        ModelAndView modelAndView;
        try {

            simpleUser.setDob(dobDate);

            if (userAvatar.getSize() != 0) {
                Photo photo = new Photo();
                photo.setImage(userAvatar.getBytes());
                photo.setName(simpleUser.getEmail() + " avatar");
                simpleUser.setPhotoId(createPhoto(photo));
            }
            WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/simple");
            ClientResponse response = webResource
                    .type(MediaType.APPLICATION_JSON)
                    .entity(simpleUser)
                    .post(ClientResponse.class);
            if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "success");
            modelAndView.addObject("message", "new SimpleUser was successfully registered");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "error");
            modelAndView.addObject("message", "registration failed!");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/shopAdmin", method = RequestMethod.POST)
    public ModelAndView registerShopAdmin(@ModelAttribute("shopadmin")ShopAdminUser shopAdminUser,
                                          @RequestParam("userAvatar") MultipartFile userAvatar,
                                          @RequestParam("dobDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dobDate) {
        ModelAndView modelAndView;
        try {

            shopAdminUser.setDob(dobDate);

            if (userAvatar.getSize() != 0) {
                Photo photo = new Photo();
                photo.setImage(userAvatar.getBytes());
                photo.setName(shopAdminUser.getEmail() + " avatar");
                shopAdminUser.setPhotoId(createPhoto(photo));
            }
            WebResource webResource = client.resource(webserviceRootUrl + "/resources/users/admin");
            ClientResponse response = webResource
                    .type(MediaType.APPLICATION_JSON)
                    .entity(shopAdminUser)
                    .post(ClientResponse.class);
            if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "success");
            modelAndView.addObject("message", "new Shop Administrator was successfully registered");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "error");
            modelAndView.addObject("message", "registration failed!");
            return modelAndView;
        }
    }

    private Integer createPhoto(Photo photo) {
        WebResource webResource = client.resource(webserviceRootUrl + "/resources/photos");
        ClientResponse response = webResource
                .entity(photo)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);
        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response.getEntity(Integer.class);
    }




    @RequestMapping(value = "/shop", method = RequestMethod.POST)
    public ModelAndView registerShop(@ModelAttribute Shop shop,
                                           @RequestParam(value = "primaryPhoto") MultipartFile primaryPhoto) {
        ModelAndView modelAndView;
        try {

            if (primaryPhoto.getSize() != 0) {
                Photo photo = new Photo();
                photo.setImage(primaryPhoto.getBytes());
                photo.setName(shop.getName() + " primary");
                shop.setPrimaryPhotoId(createPhoto(photo));
            }
            WebResource webResource = client.resource(webserviceRootUrl + "/resources/shops");
            ClientResponse response = webResource
                    .type(MediaType.APPLICATION_JSON)
                    .entity(shop)
                    .post(ClientResponse.class);
            if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "success");
            modelAndView.addObject("message", "new Shop was successfully registered");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView = new ModelAndView("public/result/on_result");
            modelAndView.addObject("title", "error");
            modelAndView.addObject("message", "registration failed!");
            return modelAndView;
        }
    }


}
