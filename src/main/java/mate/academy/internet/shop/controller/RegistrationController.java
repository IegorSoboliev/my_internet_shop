package mate.academy.internet.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.exceptions.EmailAlreadyRegisteredException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;
import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("user_name"));
        user.setSurname(req.getParameter("user_surname"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("psw"));
        try {
            userService.create(user);
        } catch (EmailAlreadyRegisteredException e) {
            LOGGER.error(e);
            req.setAttribute("errorEmailAlready", "This email already registered");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/dataProcessingProblem.jsp")
                    .forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
