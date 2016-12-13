package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.model.MealsInMemory;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by bobans on 10.12.16.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);
    MealDaoMemory meals = new MealDaoMemory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost meals");
        request.setCharacterEncoding("UTF-8");
        if ((request.getParameter("type") != null) && (request.getParameter("type").equals("add"))) {
            LOG.debug("Add meals");
            meals.doAdd(new Meal(meals.getLastId(),
                    //request.getParameter("Date"),
                    LocalDateTime.now(),
                    request.getParameter("Description"),
                    Integer.parseInt(request.getParameter("Calories"))
            ));
        } else if ((request.getParameter("type") != null)
                && (request.getParameter("type").equals("update"))
                && (request.getParameter("id") != null)) {
            Meal temp = new Meal(Integer.parseInt(request.getParameter("id")),
                    //request.getParameter("Date"),
                    LocalDateTime.now(),
                    request.getParameter("Description"),
                    Integer.parseInt(request.getParameter("Calories"))
            );
            LOG.debug("Update meals = " + temp.toString());
            meals.doUpdate(temp);

        }
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet meals");

        if ((request.getParameter("step") != null) && (request.getParameter("step").equals("add"))) {
            request.getRequestDispatcher("/add.jsp").forward(request, response);
        } else if ((request.getParameter("step") != null) && (request.getParameter("step").equals("update")) && (request.getParameter("id") != null)) {
            request.setAttribute("Date", meals.get(Integer.parseInt(request.getParameter("id"))).getDateTime());
            request.setAttribute("Description", meals.get(Integer.parseInt(request.getParameter("id"))).getDescription());
            request.setAttribute("Calories", meals.get(Integer.parseInt(request.getParameter("id"))).getCalories());
            request.setAttribute("id", request.getParameter("id"));
            request.getRequestDispatcher("/update.jsp").forward(request, response);
        } else if ((request.getParameter("step") != null) && (request.getParameter("step").equals("delete")) && (request.getParameter("id") != null)) {
            meals.doDelete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("meals");
        } else {
            List<MealWithExceed> mealsListExceed = MealsUtil.getFilteredWithExceeded(meals.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("mealsList", mealsListExceed);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }
}
