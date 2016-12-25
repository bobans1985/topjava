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

    public static final List< Meal> userMeals = new ArrayList<>();
    public static final Map<Integer, Meal> adminMeals = new ConcurrentHashMap<>();

    {
        userMeals.add(new Meal(START_SEQ+2,LocalDateTime.parse("2016-12-21T11:00:00"),"Test1",150));
        userMeals.add(new Meal(START_SEQ+3,LocalDateTime.parse("2016-12-22T23:00:00"),"Test2",500));
        userMeals.add(new Meal(START_SEQ+4,LocalDateTime.parse("2016-12-23T03:00:00"),"Test3",1000));
        userMeals.add(new Meal(START_SEQ+5,LocalDateTime.parse("2016-12-23T15:00:00"),"Test4",1000));

        adminMeals.put(UserTestData.ADMIN_ID,new Meal(START_SEQ+6,LocalDateTime.parse("2016-12-26T00:40:00"),"Admin1",700));
        adminMeals.put(UserTestData.ADMIN_ID,new Meal(START_SEQ+7,LocalDateTime.parse("2016-12-27T08:41:00"),"Admin2",4000));

    }

    public List<Meal> getAll() {
        return userMeals.stream().sorted(new Comparator<Meal>() {
            @Override
            public int compare(Meal o1, Meal o2) {
                return o2.getDateTime().compareTo(o1.getDateTime());
            }
        }).collect(Collectors.toList());
    }

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getDescription(),actual.getDescription())
//                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );

}
