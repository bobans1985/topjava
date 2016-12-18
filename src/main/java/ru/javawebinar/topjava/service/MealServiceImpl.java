package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

/**
 * GKislin
 * 06.03.2015.
 */
public class MealServiceImpl implements MealService {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealRepository repository;

    {
        repository = new InMemoryMealRepositoryImpl();
    }

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    /*@Override
    public void delete(int id) throws NotFoundException {
        if (!repository.delete(id))  throw  new NotFoundException("Error delete");
    }*/

    @Override
    public void delete(int id, int userid) throws NotFoundException {
        if (!repository.delete(id, userid)) throw new NotFoundException("Error delete");
    }


    /*@Override
    public Meal get(int id) throws NotFoundException {
        return repository.get(id);
    }*/

    @Override
    public Meal get(int id, int userid) throws NotFoundException {
        Meal meal = repository.get(id, userid);
        if (meal == null) throw new NotFoundException("Error get");
        return meal;
    }

    @Override
    public List<Meal> getAll() {
        return repository.getAll().stream().collect(Collectors.toList());
    }

    @Override
    public void update(Meal meal, int userid) throws NotFoundException {
        if (repository.get(meal.getId(),userid) ==null) throw new NotFoundException("Error update");
        else repository.save(meal);
    }


}
