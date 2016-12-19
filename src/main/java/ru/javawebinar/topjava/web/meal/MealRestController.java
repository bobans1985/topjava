package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
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
        LOG.info("getWithExceeded");
        return MealsUtil.getWithExceeded(service.getAll(), AuthorizedUser.getCaloriesPerDay(), AuthorizedUser.id());
    }

    public List<MealWithExceed> getFilteredWithExceeded(String sDate, String sTime, String eDate, String eTime) {
        LOG.info("getFilteredWithExceeded sDate={},sTime={},eDate={},eTime={}", sDate, sTime, eDate, eTime);
        LocalDate startDate = sDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(sDate);
        LocalTime startTime = sTime.isEmpty() ? LocalTime.MIN : LocalTime.parse(sTime);
        LocalDate endDate = eDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(eDate);
        LocalTime endTime = eTime.isEmpty() ? LocalTime.MAX : LocalTime.parse(eTime);
        List<MealWithExceed> result = MealsUtil.getFilteredWithExceeded(service.getAll(), startTime, endTime, AuthorizedUser.getCaloriesPerDay(), AuthorizedUser.id())
                .stream()
                .filter(u1 -> DateTimeUtil.isBetweenDate(u1.getDateTime().toLocalDate(), startDate, endDate))
                .collect(Collectors.toList());
        return result;
    }


    public Meal get(int id) throws NotFoundException {
        LOG.info("get id={}", id);
        return service.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        meal.setId(null);
        LOG.info("create {}", meal);
        return service.save(meal);
    }

    public void delete(int id) throws NotFoundException {
        LOG.info("delete id={}",id);
        service.delete(id, AuthorizedUser.id());
    }

    public void update(Meal meal, int id) throws NotFoundException {
        meal.setId(id);
        LOG.info("update {}",meal);
        service.update(meal, AuthorizedUser.id());
    }


}
