package com.lapeevvd.controller.json;

import com.lapeevvd.MealTestData;
import com.lapeevvd.model.UserMeal;
import org.junit.Test;

import java.util.List;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(MealTestData.ADMIN_MEAL);
        System.out.println(json);
        UserMeal userMeal = JsonUtil.readValue(json, UserMeal.class);
        MealTestData.MATCHER.assertEquals(MealTestData.ADMIN_MEAL, userMeal);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(MealTestData.USER_MEALS);
        System.out.println(json);
        List<UserMeal> userMeals = JsonUtil.readValues(json, UserMeal.class);
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.USER_MEALS, userMeals);
    }
}