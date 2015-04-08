package com.foodservice.resources;

import com.foodservice.entities.Message;
import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/massages")
public class MassageResource {

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Message message) {
        try {
            Integer id = messageService.create(message);
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
        Message message = messageService.get(id);
        if (message != null)
            return Response.ok(message).status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/dialog")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDialogMessages(@PathParam("user1Id") int user1Id,
                                      @PathParam("user2Id") int user2Id,
                                    @DefaultValue("0") @QueryParam("firstResult") int firstResult,
                                    @DefaultValue("1000000") @QueryParam("maxResults") int maxResults) {
        List<Message> messages = messageService.getDialogMessages(user1Id, user2Id, firstResult, maxResults);
        if (messages.size() != 0)
            return Response.ok(messages).status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Message message) {
        try {
            messageService.update(message);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Message message) {
        try {
            messageService.delete(message);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
