package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.AuthorizedUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class UserServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LOG.debug("forward to users");
        request.setAttribute("info","");
        String userid=request.getParameter("userid");
        if (userid!=null) {
            AuthorizedUser.setId(Integer.parseInt(userid));
            request.setAttribute("info","Пользователь успешно изменен на userid="+userid);
        }
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
