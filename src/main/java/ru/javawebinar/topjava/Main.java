package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
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

        //springContext =  new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        springContext.getEnvironment().setActiveProfiles(Profiles.ACTIVE_DB,Profiles.ACTIVE_METHOD);
        springContext.load("spring/spring-app.xml", "spring/spring-db.xml");
        springContext.refresh();


        mealController = springContext.getBean(MealRestController.class);
        System.out.println(mealController);
    }
}
