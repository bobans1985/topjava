package ru.javawebinar.topjava.service;

import ch.qos.logback.core.util.TimeUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import ru.javawebinar.topjava.MealTestData.*;
import sun.plugin.javascript.navig.Array;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.userMeals;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


/**
 * Created by bobans on 25.12.16.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    private static final Comparator<Meal> MEAL_COMPARATOR = Comparator.comparing(Meal::getDateTime).reversed();

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        //System.out.println("setUp");
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        MATCHER.assertEquals(userMeals.get(1), service.get(100003, USER_ID));
    }

    @Test
    public void testGetNotUser() throws Exception {
        try {
            MATCHER.assertEquals(userMeals.get(1), service.get(100006, USER_ID));
        } catch (NotFoundException ex) {
            //ex.printStackTrace();
        }
    }

    @Test
    public void testDelete() throws Exception {
        List<Meal> list = new ArrayList<>(userMeals);
        list.remove(1);
        service.delete(100003, USER_ID);
        MATCHER.assertCollectionEquals(list.stream().sorted(MEAL_COMPARATOR).collect(Collectors.toList()), service.getAll(USER_ID));
    }

    @Test
    public void testDeleteNotUser() throws Exception {
        try {
            List<Meal> list = new ArrayList<>(userMeals);
            list.remove(1);
            service.delete(100006, USER_ID);
            MATCHER.assertCollectionEquals(list.stream().sorted(MEAL_COMPARATOR).collect(Collectors.toList()), service.getAll(USER_ID));
        } catch (NotFoundException ex) {
            //ex.printStackTrace();
        }
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        LocalDate start_date = LocalDate.of(2014, 12, 12);
        LocalDateTime start = LocalDateTime.of(start_date, LocalTime.MIN);
        LocalDate end_date = LocalDate.of(2016, 12, 12);
        LocalDateTime end = LocalDateTime.of(end_date, LocalTime.MAX);

        List<Meal> list1 = new ArrayList<>(userMeals).stream()
                .filter(u1 -> DateTimeUtil.isBetween(u1.getDateTime(), start, end))
                .sorted(MEAL_COMPARATOR)
                .collect(Collectors.toList());
        List<Meal> list2 = (List<Meal>) service.getBetweenDateTimes(start, end, USER_ID);
        MATCHER.assertCollectionEquals(list1, list2);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        LocalDateTime start = LocalDateTime.of(2014, 12, 12, 7, 0);
        LocalDateTime end = LocalDateTime.now();
        List<Meal> list1 = new ArrayList<>(userMeals).stream()
                .filter(u1 -> DateTimeUtil.isBetween(u1.getDateTime(), start, end))
                .sorted(MEAL_COMPARATOR)
                .collect(Collectors.toList());
        List<Meal> list2 = (List<Meal>) service.getBetweenDateTimes(start, end, USER_ID);
        MATCHER.assertCollectionEquals(list1, list2);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(userMeals.stream().sorted(MEAL_COMPARATOR).collect(Collectors.toList()), service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = new Meal(userMeals.get(1));
        meal.setDescription("updated");
        service.update(meal, USER_ID);
        MATCHER.assertEquals(meal, service.get(100003, USER_ID));
    }


    @Test
    public void testUpdateNotUser() throws Exception {
        try {
            Meal meal = new Meal(userMeals.get(1));
            meal.setDescription("updated");
            service.update(meal, USER_ID);
            MATCHER.assertEquals(meal, service.get(100006, USER_ID));
        } catch (NotFoundException ex) {
            //ex.printStackTrace();
        }
    }


    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.parse("2016-12-21T11:00:00"), "bobans", 150);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        List<Meal> list = new ArrayList<>(userMeals);
        list.add(newMeal);
        MATCHER.assertCollectionEquals(list.stream().sorted(MEAL_COMPARATOR).collect(Collectors.toList()), service.getAll(USER_ID));

    }
}