package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.repository.dataObjects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class MealsUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static void main(String[] args) {
        /*List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(dataObjects.MEALS, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000, dataObjects.USERS.get(2));
        mealsWithExceeded.forEach(System.out::println);

        System.out.println(getFilteredWithExceededByCycle(dataObjects.MEALS, LocalTime.of(7, 0), LocalTime.of(12, 0), DEFAULT_CALORIES_PER_DAY));*/
        //InMemoryUserRepositoryImpl users = new InMemoryUserRepositoryImpl();
       // System.out.println(users.getAll());
        System.out.println(getWithExceeded(dataObjects.MEALS, DEFAULT_CALORIES_PER_DAY, 2));
    }

    public static List<MealWithExceed> getWithExceeded(Collection<Meal> meals, int caloriesPerDay, int userid) {
        return getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay, userid);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay, int userid) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .filter(meal -> meal.getUserid()==userid)
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );
        try {
            return meals.stream()
                    .filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                    .filter(meal -> meal.getUserid()==userid)
                    .sorted(new Comparator<Meal>() {
                        @Override
                        public int compare(Meal o1, Meal o2) {
                            return o2.getDateTime().compareTo(o1.getDateTime());
                        }
                    })
                    .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                    .collect(Collectors.toList());
        } catch (NullPointerException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    public static List<MealWithExceed> getFilteredWithExceededByCycle(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        meals.forEach(meal -> caloriesSumByDate.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        final List<MealWithExceed> mealsWithExceeded = new ArrayList<>();
        meals.forEach(meal -> {
            if (DateTimeUtil.isBetween(meal.getTime(), startTime, endTime)) {
                mealsWithExceeded.add(createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay));
            }
        });
        return mealsWithExceeded;
    }

    public static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded, meal.getUserid());
    }
}