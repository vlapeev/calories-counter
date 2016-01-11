package com.lapeevvd.web;

import com.lapeevvd.controller.AdminController;
import com.lapeevvd.model.User;
import com.lapeevvd.repository.UserRepository;
import com.lapeevvd.util.exception.NotFoundException;
import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static com.lapeevvd.UserTestData.*;

public class AdminTest {

    private static ConfigurableApplicationContext springContext;
    private static AdminController controller;
    @BeforeClass
    public static void beforeClass(){
        springContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        controller = springContext.getBean(AdminController.class);
    }

    @Before
    public void setUp() throws Exception{
        UserRepository repository = springContext.getBean(UserRepository.class);
        //эта строка под вопросом
        repository.getAll().forEach(user -> repository.delete(user.getId()));
        repository.save(USER);
        repository.save(ADMIN);
    }

    @Test
    public void testDelete() throws Exception{
        controller.delete(USER_ID);
        List<User> users = controller.getAll();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception{
        controller.delete(9000);
    }

    @AfterClass
    public static void afterClass(){
        springContext.close();
    }
}
