package com.lapeevvd.repository.datajpa;

import com.lapeevvd.model.UserMeal;
import com.lapeevvd.repository.UserMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{

    @Autowired
    private ProxyUserMealRepository proxy;

    /*@PersistenceContext
    private EntityManager em;*/

    @Autowired
    private ProxyUserRepository userProxy;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (!userMeal.isNew() && userMeal.getId() != 0 && get(userMeal.getId(), userId) == null) {
            return null;
        }
        userMeal.setUser(userProxy.findOne(userId));
        /*User ref = em.getReference(User.class, userId);
        userMeal.setUser(ref);*/
        return proxy.save(userMeal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.findOne(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.getAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime start, LocalDateTime end, int userId) {
        return proxy.findBetween(start, end, userId);
    }

    @Override
    public UserMeal getWithUser(int id, int userId) {
        return proxy.getWithUser(id, userId);
    }
}
