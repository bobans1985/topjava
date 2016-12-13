package ru.javawebinar.topjava.dao;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealsInMemory;
import java.util.Iterator;

/**
 * Created by bobans on 10.12.16.
 */
public class MealDaoMemory implements IMealDao {
     MealsInMemory meals;


    public MealDaoMemory(MealsInMemory meals) {
        this.meals=meals;
    }

    public void doAdd(Meal meal) {
        this.meals.add(meal);
    }

    public Meal get(int id) {
        Meal res= this.meals.getAll().get(0);
        for (Meal temp : meals.getAll()) {
            if (temp.getId()==id) res=temp;
        }
        return res;
    }

    public void doUpdate(Meal meal) {
        doDelete(meal.getId());
        doAdd(meal);
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

}
