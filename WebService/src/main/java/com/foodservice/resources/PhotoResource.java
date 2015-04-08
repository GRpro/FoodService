package com.foodservice.resources;

import com.foodservice.entities.Photo;
import com.foodservice.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/photos")
public class PhotoResource {

    private PhotoService photoService;

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Photo message) {
        try {
            Integer id = photoService.create(message);
            return Response.ok(id).status(Response.Status.CREATED).build();
        } catch (Exception e)  {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") int id) {
        Photo message = photoService.get(id);
        if (message != null)
            return Response.ok(message).status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Photo message) {
        try {
            photoService.update(message);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Photo message) {
        try {
            photoService.delete(message);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
