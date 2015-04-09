package com.foodservice.resources;

import com.foodservice.entities.data.Gender;
import com.foodservice.entities.data.State;
import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.user.ManagerUser;
import com.foodservice.entities.user.SimpleUser;
import com.foodservice.exceptions.NoSuchUserException;
import com.foodservice.services.ManagerUserService;
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
@Path("/users/manager")
public class ManagerUserResource {

    private ManagerUserService managerUserService;

    @Autowired
    public void setManagerUserService(ManagerUserService managerUserService) {
        this.managerUserService = managerUserService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@QueryParam("shopAdminUserEmail") String shopAdminUserEmail,
                           ManagerUser managerUser) {
        try {
            Integer id = managerUserService.create(managerUser, shopAdminUserEmail);
            return Response.ok(id).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * Returns list of ManagerUser related to ShopAdmin with particular id
     * @param shopAdminID identifier of ShopAdminUser
     * @return list of managers
     */
    @GET
    @Path("/byShopAdminID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByShopAdminUserID(@QueryParam("shopAdminID") int shopAdminID) {
        try {
            List<ManagerUser> managerUsers = managerUserService.getByShopAdminUserID(shopAdminID);
            return Response.ok(managerUsers).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") int id) {
        try {
            ManagerUser managerUser = managerUserService.get(id);
            return Response.ok(managerUser).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/byCriterion")
    @Produces("application/javascript")
    public JSONWithPadding getByCriterion(@DefaultValue("") @QueryParam("firstNameLike") String firstNameLike,
                                          @DefaultValue("") @QueryParam("lastNameLike") String lastNameLike,
                                          @DefaultValue("0") @QueryParam("minAge") Integer minAge,
                                          @DefaultValue("100") @QueryParam("maxAge") Integer maxAge,
                                          @DefaultValue("") @QueryParam("systemStatus") SystemStatus systemStatus,
                                          @DefaultValue("") @QueryParam("gender") Gender gender,
                                          @DefaultValue("0") @QueryParam("firstResult") int firstResult,
                                          @DefaultValue("10000") @QueryParam("maxResults") int maxResults,
                                          @QueryParam("callback") String callback) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("firstNameLike", firstNameLike);
            map.put("lastNameLike", lastNameLike);
            map.put("ageMax", minAge);
            map.put("ageMin", maxAge);
            map.put("gender", gender);
            map.put("systemStatus", systemStatus);
            List<ManagerUser> managerUsers = managerUserService.getByCriterion(map);
            return new JSONWithPadding(new GenericEntity<List<ManagerUser>>(managerUsers) {}, callback);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Path("/byEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("email") String email) {
        try {
            ManagerUser managerUser = managerUserService.getByEmail(email);
            return Response.ok(managerUser).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ManagerUser managerUser) {
        try {
            managerUserService.update(managerUser);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/state")
    public Response updateState(@QueryParam("id") Integer id,
                                @QueryParam("state") State state) {
        try {
            managerUserService.updateState(id, state);
            return Response.status(Response.Status.OK).build();
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(ManagerUser managerUser) {
        try {
            managerUserService.delete(managerUser);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
