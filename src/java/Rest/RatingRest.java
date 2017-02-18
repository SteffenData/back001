/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entity.Rating;
import Facade.RatingFacade;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author juhlm
 */
@Path("Rating/")
public class RatingRest {

    @GET
    @Path("{id}")
    @Consumes("application/json")
    public Response getRatingForSkill(@PathParam("id") String id) {

        RatingFacade rf = new RatingFacade();
        List<Rating> ratingList;
        ratingList = rf.getRatingForOneSkill(id);
        JsonArray jsonRatingArray = new JsonArray();

        for (Rating rating : ratingList) {

            JsonObject jo = new JsonObject();

            jo.addProperty("score", rating.getScore());
            jo.addProperty("comment", rating.getComment());

            jsonRatingArray.add(jo);
        }

        return Response.status(Response.Status.OK).entity(jsonRatingArray.toString()).build();
    }

    @POST
    @Path("addRating/{id}")
    @Consumes("application/json")
    public void addRatingToSkill(String rating,@PathParam("id")String id){
    
     JsonObject jsonRating = new JsonParser().parse(rating).getAsJsonObject(); 
     int score = jsonRating.get("score").getAsInt();
     String comment = jsonRating.get("comment").getAsString();
     
     Rating ratingToAdd = new Rating(score, comment);
     
     RatingFacade rf = new RatingFacade();
     
     rf.insertRatingToSkill(ratingToAdd, id);
    }

}
