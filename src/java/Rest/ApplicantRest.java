/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entity.Applicant;
import Facade.ApplicantFacade;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import jdk.nashorn.internal.parser.JSONParser;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author juhlm
 */
@Path("Applicant/")
public class ApplicantRest {
    
 @POST
 @Path("addApplicant")
 @Consumes("application/json")
 public void saveUser (String user){
 
     JsonObject jsonUser = new JsonParser().parse(user).getAsJsonObject();
     String firstname = jsonUser.get("firstName").getAsString();
     String lastName = jsonUser.get("lastName").getAsString();
     int age = jsonUser.get("age").getAsInt();
     
     Applicant applicant = new Applicant(firstname,lastName,age);
     
     ApplicantFacade uf = new ApplicantFacade();
     uf.insertApplicantInDatabase(applicant);
 }
 
 @GET
 @Path("{id}")
 @Consumes("application/json")
 public Response getUser(@PathParam("id") String id)
 {
     JsonObject jsonPerson = new JsonObject();
     Applicant applicant = new Applicant();
     ApplicantFacade uf = new ApplicantFacade();
     applicant = uf.getApplicantFromDatabase(id);
     
     jsonPerson.addProperty("firstName", applicant.getFirstName());
     jsonPerson.addProperty("lastName", applicant.getLastName());
     jsonPerson.addProperty("age", applicant.getAge());
     
     return Response.status(Response.Status.OK).entity(jsonPerson.toString()).build();
 }
 
 @GET
 @Path("allApplicants")
 @Consumes("application/json")
 public Response getAllApplicants(){
     
     List<Applicant> applicantsList;
     JsonArray jsonApplicantsArray = new JsonArray();
     
     ApplicantFacade uf = new ApplicantFacade();
     applicantsList = uf.getAllApplicants();
     
     for(Applicant applicant : applicantsList ){
        
         JsonObject jsonApplicant = new JsonObject();
         
         jsonApplicant.addProperty("id", applicant.getId());
         jsonApplicant.addProperty("firstName", applicant.getFirstName());
         jsonApplicant.addProperty("lastName", applicant.getLastName());
         jsonApplicant.addProperty("age", applicant.getAge());
         
         jsonApplicantsArray.add(jsonApplicant);
     }
     
     return Response.status(Response.Status.OK).entity(jsonApplicantsArray.toString()).build();
 }
    
}
