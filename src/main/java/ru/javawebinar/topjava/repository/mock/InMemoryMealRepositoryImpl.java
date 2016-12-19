package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.dataObjects;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
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
        LOG.debug("meal new: {}",meal);
        return meal;
    }


    @Override
    public boolean delete(int id,int userid) {
        LOG.debug("meal delete {}", id);
        try {
            if (repository.get(id).getUserid() == userid) {
                repository.remove(id);
                return true;
            } else return false;
        } catch (NullPointerException ex)
        {
            return false;
        }
    }

    @Override
    public Meal get(int id,int userid) {
        LOG.debug("meal get: {} ", id);
        return repository.get(id).getUserid()==userid?repository.get(id):null;
    }

    @Override
    public Collection<Meal> getAll() {
        LOG.debug("getAll for all users");
        return repository.values();
    }

}

