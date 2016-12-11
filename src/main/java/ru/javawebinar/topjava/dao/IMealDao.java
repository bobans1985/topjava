package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

/**
 * Created by bobans on 10.12.16.
 */
public interface IMealDao {
    void doCreate(Meal meal);
    Meal doRead(int id);
    void doUpdate(Meal meal);
    void doDelete(int id);
}
