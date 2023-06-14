package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.BookingDTO;
import errorhandling.API_Exception;
import facades.BookingFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("booking")
public class BookingResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final BookingFacade FACADE = BookingFacade.getBookingFacade(EMF);


    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBoats() {
        List<BookingDTO> bookingDTOS = FACADE.getAllBookings();
        return Response.ok().entity(GSON.toJson(bookingDTOS)).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(String jsonString) throws API_Exception {
        LocalDateTime date_and_time;
        int duration;
        String regNum;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            date_and_time = LocalDateTime.parse(json.get("date_and_time").getAsString());
            duration = json.get("duration").getAsInt();
            regNum = json.get("regNum").getAsString();

        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied", 400, e);
        }
        try {
            FACADE.createBooking(date_and_time, duration,regNum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.ok().entity(GSON.toJson("Booking has successfully been created")).build();

    }

}
