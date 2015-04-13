package com.foodservice.resources;

import com.foodservice.entities.Shop;
import com.foodservice.entities.data.Gender;
import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.user.SimpleUser;
import com.foodservice.services.SimpleUserService;
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
@Path("/users/simple")
public class SimpleUserResource {
    
    private SimpleUserService simpleUserService;

    @Autowired
    public void setSimpleUserService(SimpleUserService simpleUserService) {
        this.simpleUserService = simpleUserService;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(SimpleUser simpleUser) {
        try {
            Integer id = simpleUserService.create(simpleUser);
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
            SimpleUser simpleUser = simpleUserService.get(id);
            return Response.ok(simpleUser).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //*******************************friendship support-start*************************//

    @GET
    @Path("/friendsOf")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriends(@QueryParam("id") int id,
                               @DefaultValue("") @QueryParam("firstNameLike") String firstNameLike,
                               @DefaultValue("") @QueryParam("lastNameLike") String lastNameLike,
                               @QueryParam("minAge") Integer minAge,
                               @QueryParam("maxAge") Integer maxAge,
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
            List<SimpleUser> simpleUsers = simpleUserService.getFriends(id, map);
            System.out.println("simpleUser's friends size: " + simpleUsers.size());
            return Response.ok(simpleUsers).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/followersOf")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowers(@QueryParam("id") int id,
                                 @DefaultValue("") @QueryParam("firstNameLike") String firstNameLike,
                                 @DefaultValue("") @QueryParam("lastNameLike") String lastNameLike,
                                 @QueryParam("minAge") Integer minAge,
                                 @QueryParam("maxAge") Integer maxAge,
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
            List<SimpleUser> simpleUsers = simpleUserService.getFollowers(id, map);
            return Response.ok(simpleUsers).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/followedBy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowedBy(@QueryParam("id") int id,
                                  @DefaultValue("") @QueryParam("firstNameLike") String firstNameLike,
                                  @DefaultValue("") @QueryParam("lastNameLike") String lastNameLike,
                                  @QueryParam("minAge") Integer minAge,
                                  @QueryParam("maxAge") Integer maxAge,
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
            List<SimpleUser> simpleUsers = simpleUserService.getFollowedBy(id, map);
            return Response.ok(simpleUsers).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/requestedTo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestedTo(@QueryParam("id") int id,
                                   @DefaultValue("") @QueryParam("firstNameLike") String firstNameLike,
                                   @DefaultValue("") @QueryParam("lastNameLike") String lastNameLike,
                                   @QueryParam("minAge") Integer minAge,
                                   @QueryParam("maxAge") Integer maxAge,
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
            List<SimpleUser> simpleUsers = simpleUserService.getRequestedTo(id, map);
            System.out.println("REQUESTED TO size: " + simpleUsers.size());
            return Response.ok(simpleUsers).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/requestedBy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequestedBy(@QueryParam("id") int id,
                                   @DefaultValue("") @QueryParam("firstNameLike") String firstNameLike,
                                   @DefaultValue("") @QueryParam("lastNameLike") String lastNameLike,
                                   @QueryParam("minAge") Integer minAge,
                                   @QueryParam("maxAge") Integer maxAge,
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
            List<SimpleUser> simpleUsers = simpleUserService.getRequestedBy(id, map);
            for (SimpleUser simpleUser :simpleUsers)
                System.out.println(simpleUser);
            System.out.println("REQUESTED BY size: " + simpleUsers.size());
            return Response.ok(simpleUsers).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    //*******************************friendship support-end*************************//

    @GET
    @Path("/byCriterion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCriterion(@DefaultValue("") @QueryParam("firstNameLike") String firstNameLike,
                                   @DefaultValue("") @QueryParam("lastNameLike") String lastNameLike,
                                   @QueryParam("minAge") Integer minAge,
                                   @QueryParam("maxAge") Integer maxAge,
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
            List<SimpleUser> simpleUsers = simpleUserService.getByCriterion(map);
            return Response.ok(simpleUsers).status(Response.Status.OK).build();
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
            SimpleUser simpleUser = simpleUserService.getByEmail(email);
            return Response.ok(simpleUser).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(SimpleUser simpleUser) {
        try {
            simpleUserService.update(simpleUser);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(SimpleUser simpleUser) {
        try {
            simpleUserService.delete(simpleUser);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
    
}
