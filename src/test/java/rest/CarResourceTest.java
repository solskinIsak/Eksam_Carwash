package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Booking;
import entities.Car;
import entities.Role;
import entities.User;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class CarResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Car r1, r2;
    private static Booking b1;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        r1 = new Car("AD123412", "More text", "Even more text",1998);
        r2 = new Car("DA123331", "More text", "Even more text",2008);
//        b1 = new Booking(LocalDateTime.now(),1,r1.toString());
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        User user = new User("user", "test");
        user.addRole(userRole);
        User admin = new User("admin", "test");
        admin.addRole(adminRole);
        try {
            em.getTransaction().begin();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            em.createQuery("delete from Car").executeUpdate();
//            em.createQuery("delete from Booking").executeUpdate();
            em.persist(r1);
            em.persist(r2);
//            em.persist(b1);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //This is how we hold on to the token after login, similar to that a client must store the token somewhere
    private static String securityToken;

    //Utility method to login and set the returned securityToken
    private static void login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                //.when().post("/api/login")
                .when().post("/login")
                .then()
                .extract().path("token");
        //System.out.println("TOKEN ---> " + securityToken);
    }

    private void logOut() {
        securityToken = null;
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/car/all").then().statusCode(200);
    }

//    @Test
//    public void testCreateCar(){
//        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//        Car car = new Car("TE89889", "Testa", "Even more text",2008);
//        String json = GSON.toJson(car);
//        System.out.println(car);
//        login("admin", "test");
//        given()
//                .contentType("application/json")
//                .body(json)
//                .header("x-access-token", securityToken)
//                .post("/car/create").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("brand", equalTo(car.getBrand()));
//    }

    @Test
    public void testGetAllCars() {
        login("user", "test");
        given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .when()
                .get("/car/all").then()
                .statusCode(200)
                .body("size()", equalTo(2));
    }

    @Test
    public void testUpdateCar(){
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        r1.setBrand("PIZZA");
        String json = GSON.toJson(r1);
        login("admin", "test");
        given()
                .contentType("application/json")
                .accept("application/json")
                .header("x-access-token", securityToken)
                .body(json)
                .patch("/car/edit/"+r1.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    //ONLY BOOKING TEST BECAUSE OF DELETE
//    @Test
//    public void testDeleteBooking(){
//        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//        String json = GSON.toJson(b1);
//        System.out.println(json);
//        login("admin", "test");
//        given()
//                .contentType("application/json")
//                .accept("application/json")
//                .header("x-access-token", securityToken)
//                .delete("/booking/delete/"+b1.getId()).then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode());
//    }
}
