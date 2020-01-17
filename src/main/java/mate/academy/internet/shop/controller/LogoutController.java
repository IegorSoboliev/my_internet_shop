package mate.academy.internet.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("MATE")) {
                c.setMaxAge(0);
            }
        }
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
