package com.lapeevvd.controller.user;

import com.lapeevvd.dataTransferObject.UserTo;
import com.lapeevvd.model.User;
import com.lapeevvd.service.UserService;
import com.lapeevvd.util.logger.LoggerWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AbstractUserController {
    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        LOG.info("create " + user);
        return service.save(user);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        user.setId(id);
        LOG.info("update " + user);
        service.update(user);
    }

    public void update(UserTo userTo) {
        LOG.info("update " + userTo);
        service.update(userTo);
    }

    public User getByMail(String email) {
        LOG.info("getByEmail " + email);
        return service.getByEmail(email);
    }

    public void enabled(int id, boolean enabled) {
        LOG.info((enabled ? "enable " : "disable ") + id);
        service.enabled(id, enabled);
    }
}
