package mate.academy.internet.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.UserService;

public class GetUserOrdersController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        User user = userService.get(userId);
        List<Order> orders = orderService.getUserOrders(user);
        req.setAttribute("greeting", "userName");
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/userOrders.jsp").forward(req, resp);
    }
}
