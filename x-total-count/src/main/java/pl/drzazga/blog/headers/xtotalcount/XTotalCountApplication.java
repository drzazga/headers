package pl.drzazga.blog.headers.xtotalcount;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class XTotalCountApplication extends Application<XTotalCountConfiguration> {

    public static void main(String[] args) throws Exception {
        new XTotalCountApplication().run(args);
    }
    
    @Override
    public void run(XTotalCountConfiguration conf, Environment env) throws Exception {
        env.jersey().register(new UsersResource(new InMemoryUserRepository()));
    }
}
