package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.Washing_AssistantDTO;
import errorhandling.API_Exception;
import facades.WashAssFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("assistant")
public class WashAssResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final WashAssFacade FACADE = WashAssFacade.getWashAssFacade(EMF);


    @GET
    @Path("all")
    @Produces("application/json")
    public Response getAllAssistants() {
        List<Washing_AssistantDTO> assistants = FACADE.getAllWashingAssistants();
        return Response.ok().entity(GSON.toJson(assistants)).build();
    }

    @POST
    @Path("create")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAssistant(String jsonString) throws AuthenticationException, API_Exception {
        String name;
        String primaryLanguage;
        String years_of_experience;
        int price_pr_hour;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            name = json.get("name").getAsString();
            primaryLanguage = json.get("primaryLanguage").getAsString();
            years_of_experience = json.get("years_of_experience").getAsString();
            price_pr_hour = json.get("price_pr_hour").getAsInt();
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        try {
            FACADE.createWashingAssistant(name, primaryLanguage, years_of_experience, price_pr_hour);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.ok().entity(GSON.toJson("Washing Assistant has successfully been created")).build();
    }


}
