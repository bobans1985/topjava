package ru.javawebinar.topjava.dao;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealsInMemory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bobans on 10.12.16.
 */
public class MealDaoMemory {
    //List<Meal> meals = new ArrayList<>();
    MealsInMemory meals = new MealsInMemory();


    /*public MealDaoMemory(List<Meal> meals) {
        this.meals = meals;
    }

    public MealDaoMemory(MealsInMemory meals) {
        for (Meal meal : meals.getAll()) {
            this.meals.add(meal);
        }
    }*/


    public void doAdd(Meal meal) {
        this.meals.add(meal);
    }


    public Meal get(int id) {
        Meal res= this.meals.get(0);
        for (Meal ttt : meals.getAll()) {
            if (ttt.getId()==id) res=ttt;
        }
        return res;
    }


    public void doUpdate(Meal meal) {
        this.meals.set(meal);
       /* int i = 0;
        for (Meal ttt : meals) {
            i++;
            if (ttt == meal) {
                meals.set(i, meal);
                break;
            }
        }*/

    }

    public void doDelete(int id) {
        Iterator<Meal> it = meals.getAll().iterator();
        while (it.hasNext()) {
            Meal meal = it.next();
            if (meal.getId() == id) {
                it.remove();
            }
        }
    }

    public List<Meal> getAll() {
        return this.meals.getAll();
    }

    public int getLastId() {
        return this.meals.getLastId();
    }
}
