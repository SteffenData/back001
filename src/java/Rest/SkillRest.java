/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entity.Skills;
import Facade.SkillsFacade;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
@Path("Skills/")
public class SkillRest {
 
@GET
@Path("{id}")
@Consumes("application/json")
public Response getAllSkillsForApplicant(@PathParam("id") String id){

    List<Skills> skillList;
    
    SkillsFacade sf = new SkillsFacade();
    skillList = sf.getAllSkillsForApplicant(id);
    JsonArray jsonAllSkills = new JsonArray();

    for (Skills skill : skillList) {
        
        JsonObject jo = new JsonObject();
        
        jo.addProperty("skillName", skill.getSkillName());
        jo.addProperty("description", skill.getDescription());
        
        jsonAllSkills.add(jo);
    }

    return Response.status(Response.Status.OK).entity(jsonAllSkills.toString()).build();
}

@POST
@Path("addSkill/{id}")
@Consumes("application/json")
public void addSkillForApplicant(String skills,@PathParam("id") String id)
{
    JsonObject jsonSkill = new JsonParser().parse(skills).getAsJsonObject();
    
    String skillName = jsonSkill.get("skillName").getAsString();
    String description = jsonSkill.get("description").getAsString();
    
    Skills skill = new Skills(skillName, description);
    SkillsFacade sf = new SkillsFacade();
    sf.addSkillToApplicant(skill, id);   
}
}
