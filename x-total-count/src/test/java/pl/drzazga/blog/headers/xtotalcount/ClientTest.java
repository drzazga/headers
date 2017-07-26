package pl.drzazga.blog.headers.xtotalcount;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.IntStream;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.testing.junit.ResourceTestRule;

public class ClientTest {

    private static final Integer USERS_COUNT = 30;
    
    private static final int PAGE_SIZE = 15;
    
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new UsersResource(new InMemoryUserRepository()))
            .build();
    
    @Before
    public void setup() {
        IntStream.range(0, USERS_COUNT).forEach(i -> {
            resources.target("users").request().post(Entity.text("User " + i));
        });
    }
    
    @Test
    public void test() {
        assertEquals(USERS_COUNT, resources.target("users/count").request().get(Integer.class));
        assertEquals(PAGE_SIZE, resources.target("users").queryParam("size", PAGE_SIZE).request().get(new GenericType<List<User>>() {}).size());
        assertEquals(USERS_COUNT.toString(), resources.target("users").request().get().getHeaderString("X-Total-Count"));
    }
    
    @After
    public void tearDown(){
        resources.target("users").request().delete();
    }
}
