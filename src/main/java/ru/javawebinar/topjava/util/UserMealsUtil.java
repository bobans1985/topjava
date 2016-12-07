package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //сортировка
        Collections.sort(mealList, new Comparator<UserMeal>() {
            @Override
            public int compare(UserMeal o1, UserMeal o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });


       /*первый способ циклами*/
        Map<LocalDate, Integer> calor = new HashMap<>();
        for (UserMeal meal : mealList) {
            LocalDate date = meal.getDateTime().toLocalDate();
            /*Можно так*/
            /*if (calor.containsKey(date)) {
                calor.put(date,calor.get(date)+meal.getCalories());
            } else  calor.put(date,meal.getCalories());*/
            /*через мердж*/
            calor.merge(date, meal.getCalories(),/*(oldValue,newValue)->oldValue+newValue*/Integer::sum);
        }

        List<UserMealWithExceed> list = new ArrayList<>();
        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                UserMealWithExceed temp = new UserMealWithExceed(meal.getDateTime(),
                        meal.getDescription(),
                        meal.getCalories(),
                        (calor.getOrDefault(meal.getDateTime().toLocalDate(), 0) > caloriesPerDay)
                );
                list.add(temp);
            }
        }

        /*второй способ streamapi*/

        Map<LocalDate, Integer> calor_stream = new HashMap<>();
        mealList.stream().forEach(u -> calor_stream.merge(u.getDateTime().toLocalDate(), u.getCalories(), Integer::sum));

        List<UserMealWithExceed> list_stream = new ArrayList<>();
        mealList.stream()
                .filter(u -> TimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime, endTime))
                .forEach(u -> list_stream.add(new UserMealWithExceed(u.getDateTime(),
                        u.getDescription(),
                        u.getCalories(),
                        (calor.getOrDefault(u.getDateTime().toLocalDate(), 0) > caloriesPerDay ? true : false)))
                );


        return list;
    }
}
