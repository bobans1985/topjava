package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.Role.ROLE_ADMIN;
import static ru.javawebinar.topjava.model.Role.ROLE_USER;

/**
 * Created by boban on 16.12.2016.
 */
public class dataObjects {

    public static final List<User> USERS = Arrays.asList(
            new User(1, "admin", "admin@inbox.ru", "admin", ROLE_USER, ROLE_ADMIN),
            new User(2, "user1", "user1@inbox.ru", "password1", ROLE_USER),
            new User(3, "user2", "user2@inbox.ru", "password2", ROLE_USER)
    );
    public static final InMemoryUserRepositoryImpl users = new InMemoryUserRepositoryImpl();
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, users.get(1)),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, users.get(2)),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, users.get(2)),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, users.get(2)),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, users.get(3)),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, users.get(3))
    );

    {

    }
}
