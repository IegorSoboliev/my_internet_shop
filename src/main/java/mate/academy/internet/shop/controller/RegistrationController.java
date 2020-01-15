package mate.academy.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;
import org.apache.log4j.Logger;

public class RegistrationController extends HttpServlet {
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
        userService.create(user);
        Cookie cookie = new Cookie("MATE", user.getToken());
        resp.addCookie(cookie);
        HttpSession session = req.getSession(true);
        session.setAttribute("userId", user.getId());
        resp.sendRedirect(req.getContextPath() + "/servlet/index");
    }
}
