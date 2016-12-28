package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;


/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final List<Meal> userMeals = new ArrayList<>(Arrays.asList(
            new Meal(START_SEQ + 2, LocalDateTime.parse("2016-12-21T11:00:00"), "Test1", 150),
            new Meal(START_SEQ + 3, LocalDateTime.parse("2016-12-22T23:00:00"), "Test2", 500),
            new Meal(START_SEQ + 4, LocalDateTime.parse("2016-12-23T03:00:00"), "Test3", 1000),
            new Meal(START_SEQ + 5, LocalDateTime.parse("2016-12-23T15:00:00"), "Test4", 1000)
    ));
    public static final List<Meal> adminMeals = new ArrayList<>(Arrays.asList(
            new Meal(START_SEQ + 6, LocalDateTime.parse("2016-12-26T00:40:00"), "Admin1", 700),
            new Meal(START_SEQ + 7, LocalDateTime.parse("2016-12-27T08:41:00"), "Admin2", 4000)
    ));

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

}
