package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

       // UserRepository userRepository = (UserRepository) appCtx.getBean("mockUserRepository");
        UserRepository userRepository = appCtx.getBean(UserRepository.class);
        MealService service = appCtx.getBean(MealService.class);
        MealRestController controller = appCtx.getBean(MealRestController.class);
        //userRepository.getAll();

        System.out.println(controller.getAll());
        appCtx.close();
    }
}
