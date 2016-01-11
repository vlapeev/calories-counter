package com.lapeevvd;

import com.lapeevvd.matcher.ModelMatcher;
import com.lapeevvd.model.UserMeal;

public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
