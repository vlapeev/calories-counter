package com.lapeevvd.web;

import com.lapeevvd.controller.user.AdminRestController;
import com.lapeevvd.model.User;
import com.lapeevvd.repository.UserRepository;
import com.lapeevvd.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.lapeevvd.UserTestData.*;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/mock.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminTest {


    /* private static ConfigurableApplicationContext springContext;*/

    @Autowired
    private AdminRestController controller;

    @Autowired
    private UserRepository repository;

    /*@BeforeClass
    public static void beforeClass(){
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/mock.xml");
        controller = springContext.getBean(AdminController.class);
    }*/

    @Before
    public void setUp() throws Exception{
        /*UserRepository repository = springContext.getBean(UserRepository.class);
        //эта строка под вопросом*/
        /*repository.getAll().forEach(user -> repository.delete(user.getId()));*/
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

    /*@AfterClass
    public static void afterClass(){
        springContext.close();
    }*/
}
