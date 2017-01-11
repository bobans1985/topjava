package ru.javawebinar.topjava.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        meal.setUser(em.getReference(User.class,userId));
        if (meal.isNew()) {
            em.persist(meal);

        } else {
            if (em.find(Meal.class, meal.getId()).getUser().getId() == userId) {
                em.merge(meal);
            } else {
                meal = null;
            }
        }
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Meal ref = em.getReference(Meal.class, id);
        if (ref.getUser().getId() != userId) {
            return false;
        } else {
            em.remove(ref);
            return true;
        }

    }

    @Override
    public Meal get(int id, int userId) {
        Meal ret = em.find(Meal.class, id);
        return ret.getUser().getId() == userId ? ret : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        //List<Meal> list = em.createQuery("SELECT e FROM Meal e WHERE e.user.id=:userid  ORDER BY e.dateTime DESC", Meal.class).setParameter("userid", userId).getResultList();
        List<Meal> list = em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("userid", userId).getResultList();
        return list;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> list = em.createNamedQuery(Meal.FILTERED_SORTED, Meal.class).setParameter("userid", userId).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
        return list;
    }
}