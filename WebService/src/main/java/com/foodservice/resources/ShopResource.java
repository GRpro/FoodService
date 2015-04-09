package com.foodservice.resources;

import com.foodservice.exceptions.DuplicatedKeyException;
import com.foodservice.entities.Shop;
import com.foodservice.services.ShopService;
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
@Path("/shops")
public class ShopResource {
    
    private ShopService shopService;

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Shop shop) {
        try {
            Integer id = shopService.create(shop);
            return Response.ok(id).status(Response.Status.CREATED).build();
        } catch (DuplicatedKeyException e)  {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") int id) {
        try {
            Shop shop = shopService.get(id);
            return Response.ok(shop).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/byShopAdminID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByManagerID(@QueryParam("shopAdminUserID") Integer shopAdminUserID) {
        try {
            List<Shop> shops = shopService.getByShopAdminID(shopAdminUserID);
            return Response.ok(shops).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/byCriterion")
    @Produces("application/javascript")
    public JSONWithPadding getByCriterion(@DefaultValue("") @QueryParam("nameLike") String nameLike,
                                   @DefaultValue("0") @QueryParam("minRating") double minRating,
                                   @DefaultValue("5") @QueryParam("maxRating") double maxRating,
                                   @DefaultValue("") @QueryParam("regionLike") String regionLike,
                                   @DefaultValue("") @QueryParam("cityLike") String cityLike,
                                   @DefaultValue("") @QueryParam("streetLike") String streetLike,
                                   @DefaultValue("") @QueryParam("buildingLike") String buildingLike,
                                   @DefaultValue("0") @QueryParam("firstResult") int firstResult,
                                   @DefaultValue("10000") @QueryParam("maxResults") int maxResults,
                                   @QueryParam("callback") String callback) {
        try {
//        System.out.println("nameLike " + nameLike);
//        System.out.println("minRating "+ minRating);
//        System.out.println("maxRating "+ maxRating);
//        System.out.println("regionLike "+ regionLike);
//        System.out.println("cityLike "+ cityLike);
//        System.out.println("streetLike "+ streetLike);
//        System.out.println("buildingLike "+ buildingLike);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nameLike", nameLike);
            parameters.put("minRating", minRating);
            parameters.put("maxRating", maxRating);
            parameters.put("regionLike", regionLike);
            parameters.put("cityLike", cityLike);
            parameters.put("streetLike", streetLike);
            parameters.put("buildingLike", buildingLike);
            List<Shop> result = shopService.getByCriterion(parameters);
//            for (int i = 0; i < result.size(); i++) {
//                System.out.println(result.get(i));
//            }
            return new JSONWithPadding(new GenericEntity<List<Shop>>(result) {}, callback);
//            return Response.ok(result).status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            return null;
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Shop shop) {
        try {
            shopService.update(shop);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Shop shop) {
        try {
            shopService.delete(shop);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
