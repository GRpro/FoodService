package com.foodservice.resources;

import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.user.User;
import com.foodservice.entities.data.UserType;
import com.foodservice.services.UserCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/users")
public class UserCommonResource {

    private UserCommonService userCommonService;

    @Autowired
    public void setUserCommonService(UserCommonService userCommonService) {
        this.userCommonService = userCommonService;
    }

    @GET
    @Path("/statuses")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getSystemStatus(Integer[] identifiers) {
        try {
            SystemStatus[] statuses = userCommonService.getSystemStatus(identifiers);
            return Response.ok(statuses).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/statuses")
    public Response changeSystemStatus(@QueryParam("id") Integer id,
                                       @QueryParam("status") SystemStatus status) {
        try {
            boolean res = userCommonService.changeSystemStatus(id, status);
            if (res) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * Returns list of users who ever have a dialog with user related to this id
     * @param id userId
     * @return
     */
    @GET
    @Path("/messageRelated")
    @Produces("application/json")
    public Response getMessageRelatedUsers(@QueryParam("id") Integer id,
                                           @DefaultValue("0") @QueryParam("firstResult") int firstResult,
                                           @DefaultValue("1000000") @QueryParam("maxResults") int maxResults,
                                           @QueryParam("userType") UserType userType) {
        try {
            List<User> users = userCommonService.getMessageRelatedUsers(id, firstResult, maxResults, userType);
            return Response.ok(users).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/byEmail")
    @Produces("application/json")
    public Response getUserByEmail(@QueryParam("email") String email) {
        try {
            User user = userCommonService.getByEmail(email);
            return Response.ok(user).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
}
