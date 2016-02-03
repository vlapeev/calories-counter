package com.lapeevvd.service.datajpa;

import com.lapeevvd.MealTestData;
import com.lapeevvd.model.User;
import com.lapeevvd.service.AbstractJpaUserServiceTest;
import com.lapeevvd.util.Profiles;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.lapeevvd.MealTestData.USER_MEALS;
import static com.lapeevvd.TestUser.MATCHER;
import static com.lapeevvd.UserTestData.USER;
import static com.lapeevvd.UserTestData.USER_ID;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest{
    @Test
    public void testGetWithMeal(){
        User testUser = service.getWithMeal(USER_ID);
        MATCHER.assertEquals(USER, testUser);
        MealTestData.MATCHER.assertCollectionEquals(USER_MEALS, testUser.getMeals());
    }
}
