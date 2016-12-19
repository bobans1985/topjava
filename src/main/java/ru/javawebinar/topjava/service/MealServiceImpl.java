package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    {
        repository = new InMemoryMealRepositoryImpl();
    }

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id, int userid) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userid),id);
    }

    @Override
    public Meal get(int id, int userid) throws NotFoundException {
        Meal meal = repository.get(id, userid);
        return checkNotFoundWithId(meal,id);
    }

    @Override
    public List<Meal> getAll() {
        return repository.getAll().stream().collect(Collectors.toList());
    }

    @Override
    public void update(Meal meal, int userid) throws NotFoundException{
        checkNotFound(repository.get(meal.getId(),userid),"Error update. User not found");
        repository.save(meal);
    }


}
