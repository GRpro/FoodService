package com.foodservice.resources;

import com.foodservice.services.ShopAdminUserService;
import com.foodservice.entities.user.ShopAdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/users/admin")
public class ShopAdminUserResource {

    private ShopAdminUserService shopAdminUserService;

    @Autowired
    public void setShopAdminUserService(ShopAdminUserService shopAdminUserService) {
        this.shopAdminUserService = shopAdminUserService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ShopAdminUser managerUser) {
        try {
            Integer id = shopAdminUserService.create(managerUser);
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
        try {
            ShopAdminUser shopAdminUser = shopAdminUserService.get(id);
            return Response.ok(shopAdminUser).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/byEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("email") String email) {
        try {
            ShopAdminUser shopAdminUser = shopAdminUserService.getByEmail(email);
            return Response.ok(shopAdminUser).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ShopAdminUser managerUser) {
        try {
            shopAdminUserService.update(managerUser);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(ShopAdminUser managerUser) {
        try {
            shopAdminUserService.delete(managerUser);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
