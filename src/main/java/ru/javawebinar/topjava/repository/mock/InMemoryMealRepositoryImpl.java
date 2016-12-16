package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.dataObjects;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    {
        dataObjects.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        LOG.info("meal new: " + meal);
        return meal;
    }

    @Override
    public boolean delete(int id,User user) {
        LOG.info("meal delete: " + id);
        if (repository.get(id).getUser()==user) {
            repository.remove(id);
            return true;
        } else return false;
    }

    @Override
    public Meal get(int id,User user) {
        LOG.info("meal get: " + id);
        return repository.get(id).getUser()==user?repository.get(id):null;
    }

    @Override
    public Collection<Meal> getAll(User user) {
        LOG.info("meal getAll");
        return repository.values().stream().filter(u1->u1.getUser()==user).collect(Collectors.toList());
    }
}

