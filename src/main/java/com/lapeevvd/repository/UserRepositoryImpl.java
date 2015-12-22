package com.lapeevvd.repository;

import com.lapeevvd.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User save(User user) {
        return null;
    }

    /**
     * @return false if not found
     */
    @Override
    public boolean delete(int id) {
        return false;
    }

    /**
     * @return null if not found
     */
    @Override
    public User get(int id) {
        return null;
    }

    /**
     * @return null if not found
     */
    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
