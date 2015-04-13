package com.foodservice.resources;

import com.foodservice.entities.data.Gender;
import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.user.SimpleUser;
import com.foodservice.services.ShopAdminUserService;
import com.foodservice.entities.user.ShopAdminUser;
import com.sun.jersey.api.json.JSONWithPadding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Path("/byCriterion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCriterion(@DefaultValue("") @QueryParam("firstNameLike") String firstNameLike,
                                          @DefaultValue("") @QueryParam("lastNameLike") String lastNameLike,
                                          @DefaultValue("0") @QueryParam("minAge") Integer minAge,
                                          @DefaultValue("100") @QueryParam("maxAge") Integer maxAge,
                                          @DefaultValue("") @QueryParam("systemStatus") SystemStatus systemStatus,
                                          @DefaultValue("") @QueryParam("gender") Gender gender,
                                          @DefaultValue("0") @QueryParam("firstResult") int firstResult,
                                          @DefaultValue("10000") @QueryParam("maxResults") int maxResults) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("firstNameLike", firstNameLike);
            map.put("lastNameLike", lastNameLike);
            map.put("ageMax", minAge);
            map.put("ageMin", maxAge);
            map.put("gender", gender);
            map.put("systemStatus", systemStatus);
            List<ShopAdminUser> shopAdminUsers = shopAdminUserService.getByCriterion(map);
            return Response.ok(shopAdminUsers).status(Response.Status.OK).build();
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
