package ru.javawebinar.topjava.repository.datajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {


    {
        Logger LOG = LoggerFactory.getLogger(DataJpaMealRepositoryImpl.class);
        LOG.info("Load DataJPA repository");
    }

    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private  CrudUserRepository emUser;

    @Override
    public Meal save(Meal meal, int userId) {
        if ((!meal.isNew()) && (get(meal.getId(),userId)==null)) {
            return null;
        }
        meal.setUser(emUser.getOne(userId));
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id,userId)!=0;
        /*try {
            if (get(id, userId) != null) {
                crudRepository.delete(id);
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;*/
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = crudRepository.findOne(id);
        if (meal!=null) {
            if (meal.getUser().getId()!=userId) {
                return null;
            }
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.getAll(userId);
        //TODO
        /*return crudRepository.findAll(
                new Sort(Sort.Direction.DESC,"dateTime")).stream().filter(u->u.getUser().getId()==userId).collect(Collectors.toList());*/
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.getAllBetween(userId,startDate,endDate);
    }
}
