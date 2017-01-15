package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;


/**
 * Created by bobans on 15.01.17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB,"jdbc"})
public class MealServiceJdbcTest extends AbstractMealServiceTest {
}
