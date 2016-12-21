package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class UserServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);
    AdminRestController adminRestController;
    ConfigurableApplicationContext appCtx;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        LOG.debug("Spring beans = {}", Arrays.toString(appCtx.getBeanDefinitionNames()));
        adminRestController= (AdminRestController) appCtx.getBean("adminRestController");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LOG.debug("forward to users");
        request.setAttribute("info","");
        String userid=request.getParameter("userid");
        if (userid!=null) {
            AuthorizedUser.setId(Integer.parseInt(userid));
            request.setAttribute("info","Пользователь успешно изменен на userid="+userid );
            request.setAttribute("userid",userid );
        }
        request.setAttribute("userid",AuthorizedUser.id );
        request.setAttribute("userList",adminRestController.getAll());
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
