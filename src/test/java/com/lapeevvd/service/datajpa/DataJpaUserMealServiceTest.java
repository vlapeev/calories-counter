package com.lapeevvd.service.datajpa;

import com.lapeevvd.TestUser;
import com.lapeevvd.model.UserMeal;
import com.lapeevvd.service.AbstractUserMealServiceTest;
import com.lapeevvd.util.Profiles;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.lapeevvd.MealTestData.*;
import static com.lapeevvd.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserMealServiceTest extends AbstractUserMealServiceTest{
    @Test
    public void testGetWithUser() throws Exception{
        UserMeal adminMeal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL, adminMeal);
        TestUser.MATCHER.assertEquals(ADMIN, adminMeal.getUser());
    }
}
