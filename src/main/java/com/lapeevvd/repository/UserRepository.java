package com.lapeevvd.repository;

import com.lapeevvd.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    /**
     * @return false if not found
     */
    boolean delete(int id);

    /**
     * @return null if not found
     */
    User get(int id);

    /**
     * @return null if not found
     */
    User getByEmail(String email);

    List<User> getAll();

    default User getWithMeal(int id){
        throw new UnsupportedOperationException();
    }
}
