package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public class MealRestController {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    {
        service = new MealServiceImpl();
    }

    public List<Meal> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public List<MealWithExceed> getWithExceeded() {
        LOG.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(), AuthorizedUser.getCaloriesPerDay(), AuthorizedUser.id());
    }


    public Meal get(int id) throws NotFoundException {
        LOG.info("get " + id);
        return service.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        meal.setId(null);
        LOG.info("create " + meal);
        return service.save(meal);
    }

    public void delete(int id) throws NotFoundException {
        LOG.info("delete " + id);
        service.delete(id, AuthorizedUser.id());
    }

    public void update(Meal meal, int id) throws NotFoundException {
        meal.setId(id);
        LOG.info("update " + meal);
        // if (meal.getUserid()==AuthorizedUser.id()) {
        service.update(meal, AuthorizedUser.id());
        //} else throw new NotFoundException("update not for this user");
    }


}
