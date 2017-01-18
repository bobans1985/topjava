package ru.javawebinar.topjava;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.meal.MealRestController;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");

        //ConfigurableApplicationContext springContext;
        GenericXmlApplicationContext springContext = new GenericXmlApplicationContext();
        MealRestController mealController;
        UserService userService;
        MealService mealService;
        //springContext =  new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        springContext.getEnvironment().setActiveProfiles(Profiles.ACTIVE_DB,Profiles.ACTIVE_METHOD);
        springContext.load("spring/spring-app.xml", "spring/spring-db.xml");
        springContext.refresh();


        mealController = springContext.getBean(MealRestController.class);

        userService = springContext.getBean(UserService.class);
        mealService = springContext.getBean(MealService.class);

        System.out.println(userService.getWithMeals(100000));
        System.out.println(mealService.getWithUser(100005,100000));
    }
}
