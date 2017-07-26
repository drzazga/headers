package pl.drzazga.blog.headers.xtotalcount;

import java.util.UUID;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class UsersResource {

    private final UserRepository repository;
    
    public UsersResource(UserRepository repository) {
        this.repository = repository;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void add(String name) {
        repository.add(new User(UUID.randomUUID().toString(), name));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("number") @DefaultValue("1") int number,
                        @QueryParam("size") @DefaultValue("10") int size) {
        
        return Response.ok(repository.get(new PageRequest(number, size)))
                       .header("X-Total-Count", count())
                       .build();
    }

    @GET
    @Path("count")
    public Integer count() {
        return repository.count();
    }
    
    @DELETE
    public void deleteAll() {
        repository.removeAll();
    }
}
