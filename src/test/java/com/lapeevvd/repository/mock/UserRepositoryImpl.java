package com.lapeevvd.repository.mock;

import com.lapeevvd.model.User;
import com.lapeevvd.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private Map<Integer, User> userMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    /*{
        save(new User(1, "User", "user", "user@mail.io", Role.USER));
        save(new User(2, "Admin", "admin", "admin@gmail.com", Role.ADMIN));
    }*/

    @Override
    public User save(User user) {
        if (user.isNew()){
            user.setId(counter.incrementAndGet());
        }
        return userMap.put(user.getId(), user);
    }

    /**
     * @return false if not found
     */
    @Override
    public boolean delete(int id) {
        return userMap.remove(id) != null;
    }

    /**
     * @return null if not found
     */
    @Override
    public User get(int id) {
        return userMap.get(id);
    }

    /**
     * @return null if not found
     */
    @Override
    public User getByEmail(String email) {
        return userMap.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userMap.values().stream().sorted((u1, u2) -> u1.getName().compareTo(u2.getName())).collect(Collectors.toList());
    }
}
