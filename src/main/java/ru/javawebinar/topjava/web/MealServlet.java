package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    AuthorizedUser userLogged = new AuthorizedUser();
    MealRestController mealRestController;
    ConfigurableApplicationContext appCtx;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        LOG.debug("Spring beans = {}", Arrays.toString(appCtx.getBeanDefinitionNames()));
        //mealRestController = (MealRestController) appCtx.getBean("mealRestController");
        mealRestController =  appCtx.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        super.destroy();
        appCtx.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        try {
            Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")), userLogged.id());

            LOG.debug(meal.isNew() ? "Create {}" : "Update {}", meal);
            if (meal.isNew()) {
                mealRestController.create(meal);
            } else mealRestController.update(meal, meal.getId());
            response.sendRedirect("meals");

        } catch (NotFoundException ex) {
            LOG.error("Error doPost:", ex);
            response.sendRedirect("error.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String startDate = request.getParameter("startDate");
        String startTime = request.getParameter("startTime");
        String endDate = request.getParameter("endDate");
        String endTime = request.getParameter("endTime");
        request.setAttribute("startDate", startDate);
        request.setAttribute("startTime", startTime);
        request.setAttribute("endDate", endDate);
        request.setAttribute("endTime", endTime);

        try {
            if (action == null) {
                LOG.debug("getAll");
                if ((startDate!=null) || (startTime!=null ) || (endDate!=null) || (endTime!=null)) {
                    request.setAttribute("meals",mealRestController.getFilteredWithExceeded(startDate,startTime,endDate,endTime));
                } else  request.setAttribute("meals",mealRestController.getWithExceeded());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);

            } else if ("delete".equals(action)) {
                int id = getId(request);
                LOG.debug("Delete {}", id);
                mealRestController.delete(id);
                response.sendRedirect("meals");

            } else if ("create".equals(action) || "update".equals(action)) {
                final Meal meal = action.equals("create") ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, userLogged.id()) :
                        mealRestController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("meal.jsp").forward(request, response);
            }
        } catch (NotFoundException ex) {
            LOG.error("Error doGet:", ex);
            response.sendRedirect("error.html");
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
