package com.lapeevvd.controller.meal;

import com.lapeevvd.controller.AbstractControllerTest;
import org.junit.Test;

import static com.lapeevvd.MealTestData.MEAL1;
import static com.lapeevvd.MealTestData.MEAL_ID;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserMealControllerTest extends AbstractControllerTest {

    @Test
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
    }
}