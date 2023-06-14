package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.CarDTO;
import errorhandling.API_Exception;
import facades.CarFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("car")
public class CarResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CarFacade FACADE = CarFacade.getCarFacade(EMF);

    @POST
    @Path("create")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCar(String jsonString) throws API_Exception {
        String regNum;
        String brand;
        String make;
        int year;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            regNum = json.get("regNum").getAsString();
            brand = json.get("brand").getAsString();
            make = json.get("make").getAsString();
            year = json.get("year").getAsInt();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Malformed JSON Supplied").build();
        }
        try {
            FACADE.createCar(regNum, brand, make, year);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.ok().entity(GSON.toJson("Car has successfully been created")).build();
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars() {
        List<CarDTO> carDTOS = FACADE.getAllCars();
        return Response.ok().entity(GSON.toJson(carDTOS)).build();
    }

    @PATCH
    @Path("edit/{id}")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCar(@PathParam("id") String id, String jsonString) throws API_Exception {
        String brand;
        String make;
        int year;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            brand = json.get("brand").getAsString();
            make = json.get("make").getAsString();
            year = json.get("year").getAsInt();
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }

        try {
            FACADE.updateCar(id, brand, make, year);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.ok().entity(GSON.toJson("Boat has successfully been updated")).build();
    }

}
