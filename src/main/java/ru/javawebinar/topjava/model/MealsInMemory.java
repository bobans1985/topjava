package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bobans on 10.12.16.
 */
public class MealsInMemory {
    static List<Meal> meals = new ArrayList<>();
    static AtomicInteger id = new AtomicInteger();
    List<Meal> mealsList = Arrays.asList(
            new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public MealsInMemory() {
        if (id.get() == 0) {
            for (Meal meal : mealsList) {
                this.meals.add(meal);
                this.id.incrementAndGet();
            }
        }
    }

    public MealsInMemory(Meal meal) {
        this.meals.add(meal);
        this.id.incrementAndGet();
    }

    public MealsInMemory(List<Meal> meals) {
        for (Meal meal : meals) {
            this.meals.add(meal);
            this.id.incrementAndGet();
        }
    }

    public Meal get(int id) {
        return meals.get(id);
    }

    public Meal getOneMeal(int id) {
        return meals.get(id);
    }

    public List<Meal> getAll() {
        return this.meals;
    }

    public int getLastId() {
        return id.get();
    }

    public void add(Meal meal) {
        this.meals.add(meal);
        this.id.incrementAndGet();
    }

    public void set(Meal meal) {
        ListIterator<Meal> it = meals.listIterator();
        while (it.hasNext()) {
            Meal temp = it.next();
            if (temp.getId() == meal.getId())
                it.set(temp);
        }
        //this.meals.set(meal.getId(),meal);
    }


    public void remove(int id) {
        this.meals.remove(id);
        //this.id.decrementAndGet();
    }


}
