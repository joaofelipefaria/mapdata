package lu.joaofaria.java.mapdata.api.quarkus.model.controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("rest")
public class Endpoint {

    @Path("hello")
    @GET
    public String hello() {
        return "Hello, World!";
    }
}