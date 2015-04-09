package com.foodservice.controller;

import com.foodservice.entities.Photo;
import com.foodservice.entities.user.ShopAdminUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Date;

@Controller
public class ContentController {

    @Value("${webservice.url.root}")
    private String webserviceRootUrl;


    @RequestMapping(value = "/content/photo/{photoId}")
    public ResponseEntity<byte[]> getCustomerImage(@PathVariable("photoId") Integer photoId) throws IOException {
        final HttpHeaders headers = new HttpHeaders();

        Client client = Client.create();
        WebResource webResource = client.resource(webserviceRootUrl + "/resources/photos/byId");
        ClientResponse response = webResource
                .queryParam("id", String.valueOf(photoId))
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        Photo photo = response.getEntity(Photo.class);
//        if (image == null)
        return new ResponseEntity<byte[]>(photo.getImage(), headers, HttpStatus.OK);
    }
}
