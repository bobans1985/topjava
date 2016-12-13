package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

/**
 * Created by bobans on 10.12.16.
 */
public interface IMealDao {
    void doAdd(Meal meal);
    Meal get(int id);
    void doUpdate(Meal meal);
    void doDelete(int id);
}
