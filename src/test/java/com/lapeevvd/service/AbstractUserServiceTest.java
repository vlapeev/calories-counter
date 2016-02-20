package com.lapeevvd.service;

import com.lapeevvd.TestUser;
import com.lapeevvd.model.Role;
import com.lapeevvd.model.User;
import com.lapeevvd.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.lapeevvd.TestUser.MATCHER;
import static com.lapeevvd.UserTestData.*;

public abstract class AbstractUserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Autowired
    protected DataSource dataSource;

    @Before
    public void setUp(){
        service.evictCache();
    }

    /*@Test
    public void testSaveInDB() throws Exception {
        JdbcUserRepositoryImpl repository = new JdbcUserRepositoryImpl(dataSource);
        TestUser testUser = new TestUser(null, "John", "stupid_password", "new@dkkh.com", false, Collections.singleton(Role.ROLE_USER), 1555);
        repository.save(testUser);
    }*/

    @Test
    public void testSave() throws Exception {
        TestUser testUser = new TestUser(null, "New", "newPass", "new@gmail.com", false, Collections.singleton(Role.ROLE_USER), 1555);
        User created = service.save(testUser.asUser());
        testUser.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, testUser, USER), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new TestUser("Duplicate", "newPass", "user@yandex.ru", 2000, Role.ROLE_USER).asUser());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(USER_ID);
        MATCHER.assertEquals(USER, user);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        MATCHER.assertEquals(USER, user);

    }

    @Test
    public void testGetAll() throws Exception {
        Collection<User> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void testUpdate() throws Exception {
        TestUser updated = new TestUser(USER);
        updated.setName("UpdatedName");
        updated.setCaloriesPerDay(330);
        service.update(updated.asUser());
        MATCHER.assertEquals(updated, service.get(USER_ID));
    }
}