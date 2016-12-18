package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    Meal save(Meal meal);

    //void delete(int id) throws NotFoundException;

    void delete(int id, int userid) throws NotFoundException;

    //Meal get(int id) throws NotFoundException;

    Meal get(int id, int userid) throws NotFoundException;

    List<Meal> getAll();

    void update(Meal meal, int userid) throws NotFoundException;
}
