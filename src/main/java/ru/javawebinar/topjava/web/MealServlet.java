package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by bobans on 10.12.16.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);
    List<MealWithExceed> mealsList = Arrays.asList(
            new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500,true),
            new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000,false),
            new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500,false),
            new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000,false),
            new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500,true),
            new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510,false)
    );

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to meals");
        request.setAttribute("test","bobans");
        request.setAttribute("mealsList",mealsList);
        request.getRequestDispatcher("/meals.jsp").forward(request,response);
    }
}
