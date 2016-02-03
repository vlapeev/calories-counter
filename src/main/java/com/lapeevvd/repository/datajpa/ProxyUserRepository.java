package com.lapeevvd.repository.datajpa;

import com.lapeevvd.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProxyUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Override
    User save(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    User findOne(Integer id);

    List<User> findAll(Sort sort);

    User getByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.meals WHERE u.id=?1")
    User getWithMeal(int id);
}
