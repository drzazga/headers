package pl.drzazga.blog.headers.xtotalcount;

public class User {

    private String id;
    
    private String username;

    protected User() {}
    
    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }
    
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
