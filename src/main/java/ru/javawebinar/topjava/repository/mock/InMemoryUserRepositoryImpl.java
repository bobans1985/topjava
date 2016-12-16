package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.dataObjects;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    {
        dataObjects.USERS.forEach(
                new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        save(user);
                    }
                }
        );
    }

    @Override
    public boolean delete(int id) {
        LOG.info("user delete " + id);
        try {
            repository.remove(id);
            return true;
        } catch (NullPointerException ex) {
            LOG.error("User error {}", ex);
            return false;
        }
    }

    @Override
    public User save(User user) {
        LOG.info("User save " + user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("User get " + id);
        try {
            return repository.get(id);
        } catch (NullPointerException ex) {
            LOG.error("User error {}", ex);
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        LOG.info("User getAll");
        return repository.values().stream().sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("User getByEmail " + email);
        try {
            //TODO
            return repository.values().stream().filter(u1 -> u1.getEmail().equals(email)).findFirst().get();
            //.reduce((u1,u2)->u1).get();

        } catch (NullPointerException ex) {
            LOG.error("Error {}", ex);
            return null;
        }
    }
}
