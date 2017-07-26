package pl.drzazga.blog.headers.xtotalcount;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

    private ConcurrentHashMap<String, User> map = new ConcurrentHashMap<>();
    
    @Override
    public void add(User user) {
        map.put(user.getId(), user);
    }

    @Override
    public List<User> get(PageRequest pageRequest) {
        return new ArrayList<>(map.values()).subList(pageRequest.getOffset(), pageRequest.getSize());
    }

    @Override
    public int count() {
        return map.size();
    }
    
    @Override
    public void removeAll() {
        map.clear();
    }
}
