package mate.academy.internet.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mate.academy.internet.shop.exceptions.AuthenticationException;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;
import org.apache.log4j.Logger;

public class LoginController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("psw");
        try {
            User user = null;
            try {
                user = userService.login(email, password);
            } catch (DataProcessingException e) {
                LOGGER.error(e);
                req.getRequestDispatcher("/WEB-INF/views/dataProcessingProblem.jsp")
                        .forward(req, resp);
            }
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", user.getId());
            resp.sendRedirect(req.getContextPath() + "/index");
        } catch (AuthenticationException e) {
            req.setAttribute("errorAuthentication", "SORRY! Incorrect email or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
