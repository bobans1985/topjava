package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL2;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.MATCHER;

/**
 * Created by bobans on 15.01.17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB,"datajpa"})
public class UserServiceDataJpaTest extends AbstractUserServiceTest{
    @Autowired
    private UserService service;

    @Test
    public void testGetWithMeals() throws Exception {
        User user = service.getWithMeals(ADMIN_ID);
        Set<Meal> meals = new HashSet<>(Arrays.asList(ADMIN_MEAL1,ADMIN_MEAL2));
        ADMIN.setMeals(meals);
        MATCHER.assertEquals(ADMIN, user);
    }
}
