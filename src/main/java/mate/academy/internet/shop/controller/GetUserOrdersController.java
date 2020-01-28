package mate.academy.internet.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.UserService;
import org.apache.log4j.Logger;

public class GetUserOrdersController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GetUserOrdersController.class);
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        User user = null;
        List<Order> orders = null;
        try {
            user = userService.get(userId);
            orders = orderService.getUserOrders(user);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/dataProcessingProblem.jsp")
                    .forward(req, resp);
        }
        req.setAttribute("greeting", "userName");
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/views/userOrders.jsp").forward(req, resp);
    }
}
