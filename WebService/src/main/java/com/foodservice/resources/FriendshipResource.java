package com.foodservice.resources;

import com.foodservice.entities.data.State;
import com.foodservice.services.FriendshipService;
import com.foodservice.entities.friendship.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/friendship")
public class FriendshipResource {

    private FriendshipService friendshipService;

    @Autowired
    public void setFriendshipService(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @POST
    public Response createRelations(@QueryParam("applicantId") int applicantId,
                                    @QueryParam("acceptorId") int acceptorId,
                                    @QueryParam("state") State state) {
        try {
            Integer id = friendshipService.createRelations(applicantId, acceptorId, state);
            return Response.ok(id).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("/byCouple")
    public Response changeState(@QueryParam("applicantId") int applicantId,
                                @QueryParam("acceptorId") int acceptorId,
                                @QueryParam("state") State state) {
        try {
            boolean res = friendshipService.changeState(applicantId, acceptorId, state);
            if (res == true) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

    @PUT
    @Path("/byId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeState(@QueryParam("id") int id,
                                @QueryParam("state") State state) {
        try {
            boolean res = friendshipService.changeState(id, state);
            if (res == true) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Integer object) {
        try {
            Friendship friendship = friendshipService.get(object);
            return Response.ok(friendship).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Friendship object) {
        try {
            friendshipService.update(object);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Friendship object) {
        try {
            friendshipService.delete(object);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
