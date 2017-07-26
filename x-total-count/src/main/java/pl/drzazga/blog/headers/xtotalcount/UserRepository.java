package pl.drzazga.blog.headers.xtotalcount;

import java.util.List;

public interface UserRepository {

    void add(User user);
    
    List<User> get(PageRequest pageRequest);
    
    int count();

    void removeAll();
}
