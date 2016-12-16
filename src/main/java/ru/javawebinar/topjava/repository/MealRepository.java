package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal Meal);

    boolean delete(int id, User user);

    Meal get(int id,User user);

    Collection<Meal> getAll(User user);
}
