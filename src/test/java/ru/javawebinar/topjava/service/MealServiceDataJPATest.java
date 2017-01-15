package ru.javawebinar.topjava.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by bobans on 15.01.17.
 */

@ActiveProfiles({Profiles.ACTIVE_DB,"datajpa"})
public class MealServiceDataJpaTest extends AbstractMealServiceTest {
}
