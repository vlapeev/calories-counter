package com.lapeevvd.controller.meal;

import com.lapeevvd.TestUtil;
import com.lapeevvd.controller.AbstractControllerTest;
import com.lapeevvd.controller.json.JsonUtil;
import com.lapeevvd.model.UserMeal;
import com.lapeevvd.service.UserMealService;
import com.lapeevvd.util.UserMealUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static com.lapeevvd.MealTestData.*;
import static com.lapeevvd.UserTestData.USER;
import static com.lapeevvd.model.AbstractEntity.START_SEQ;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserMealRestControllerTest extends AbstractControllerTest {

    public static final String REST_URL = UserMealRestController.REST_URL + '/';

    @Autowired
    private UserMealService service;

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_MEAL_ID)
                .with(TestUtil.userHttpBasic(USER)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + ADMIN_MEAL_ID).contentType(MediaType.APPLICATION_JSON)
                .with(TestUtil.userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL_ID).with(TestUtil.userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL_ID).with(TestUtil.userHttpBasic(USER)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), service.getAll(START_SEQ));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();

        mockMvc.perform(put(REST_URL + MEAL_ID).with(TestUtil.userHttpBasic(USER)).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(MEAL_ID, START_SEQ));
    }

    @Test
    public void testCreate() throws Exception {
        UserMeal created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL).with(TestUtil.userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        UserMeal returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(START_SEQ));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).with(TestUtil.userHttpBasic(USER)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(UserMealUtil.getWithExceeded(USER_MEALS, USER.getCaloriesPerDay())));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "between?startDateTime=2016-01-15T07:00&endDateTime=2016-01-16T11:00:00").with(TestUtil.userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(
                        UserMealUtil.createWithExceed(MEAL4, true),
                        UserMealUtil.createWithExceed(MEAL1, false)));
    }
}