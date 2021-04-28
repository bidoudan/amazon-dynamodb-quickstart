package org.acme.dynamodb;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/fruits")
public class FruitResource {

    @Inject
    FruitSyncService service;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> getAll() {
        return service.findAll();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Fruit getSingle(@PathParam("name") String name) {
        return service.get(name);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> add(Fruit fruit) {

        return service.add(fruit);
    }
}
