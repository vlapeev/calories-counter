package com.lapeevvd.service;

import com.lapeevvd.dataTransferObject.UserTo;
import com.lapeevvd.model.User;
import com.lapeevvd.repository.UserRepository;
import com.lapeevvd.util.LoggedUser;
import com.lapeevvd.util.UserUtil;
import com.lapeevvd.util.exception.CheckException;
import com.lapeevvd.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        repository.save(UserUtil.updateFromUserTo(user, userTo));
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        CheckException.check(repository.delete(id), id);
    }

    public User get(int id) throws NotFoundException {
        return CheckException.check(repository.get(id), id);
    }

    public User getByEmail(String email) throws NotFoundException {
        Objects.requireNonNull(email, "Email must not be empty");
        return CheckException.check(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {
    }

    @Override
    public User getWithMeal(int id) {
        return repository.getWithMeal(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enabled(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.getByEmail(email);
        if (u == null){
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new LoggedUser(u);
    }
}
