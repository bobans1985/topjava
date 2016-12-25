package ru.javawebinar.topjava.service;

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
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.web.user.AdminRestController;

import ru.javawebinar.topjava.MealTestData.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by bobans on 25.12.16.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetBetweenDates() throws Exception {

    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testSave() throws Exception {
        MealTestData testData = new MealTestData();
        Meal newMeal = new Meal(null, LocalDateTime.parse("2016-12-21T11:00:00"),"bobans",150);
        Meal created = service.save(newMeal,UserTestData.USER_ID);
        newMeal.setId(created.getId());
        testData.userMeals.add(newMeal);
        MealTestData.MATCHER.assertCollectionEquals(testData.getAll(), service.getAll(UserTestData.USER_ID) );
    }
}