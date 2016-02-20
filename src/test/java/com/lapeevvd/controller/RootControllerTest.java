package com.lapeevvd.controller;

import com.lapeevvd.TestUtil;
import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static com.lapeevvd.UserTestData.ADMIN;
import static com.lapeevvd.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUserList() throws Exception {
        TestUtil.authorize(ADMIN);
        mockMvc.perform(get("/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"));
    }

    /*@Test
    public void testUserList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }*/


    /*@Test
    public void testUserList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"))
                .andExpect(model().attribute("userList", Matchers.hasSize(2)))
                .andExpect(model().attribute("userList", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty("id", Matchers.is(AbstractEntity.START_SEQ)),
                                Matchers.hasProperty("name", Matchers.is(UserTestData.USER.getName()))
                        )
                )));
    }*/

    @Test
    public void testMealList() throws Exception {
        TestUtil.authorize(USER);
        mockMvc.perform(get("/meals"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"));
    }

    /*@Test
    public void testMealList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/meals"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }*/

    /*@Test
    public void testMealList() throws Exception {
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
                .andExpect(model().attribute("mealList", hasSize(6)))
                .andExpect(model().attribute("mealList", hasItem(
                        allOf(
                                hasProperty("id", is(MEAL_ID)),
                                hasProperty("description", is(MEAL1.getDescription()))
                        )
                )));
    }*/

    @Test
    public void testUserListUnAuth() throws Exception {
        TestUtil.authorize(USER);
        mockMvc.perform(get("/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("exception"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/exception.jsp"));
    }
}