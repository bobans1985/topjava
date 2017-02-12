package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.to.UserTo;

/**
 * GKislin
 */
public class MealUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static Meal createNewFromTo(MealTo newMeal) {
        return new Meal(null, newMeal.getDateTime(), newMeal.getDescription(), newMeal.getCalories());
    }

    public static MealTo asTo(Meal meal) {
        return new MealTo(meal.getId(), meal.getDateTime(),meal.getDescription(),meal.getCalories());
    }

    public static Meal updateFromTo(Meal meal, MealTo mealTo) {
        meal.setDescription(mealTo.getDescription());
        meal.setDateTime(mealTo.getDateTime());
        meal.setCalories(mealTo.getCalories());
        return meal;
    }
}
