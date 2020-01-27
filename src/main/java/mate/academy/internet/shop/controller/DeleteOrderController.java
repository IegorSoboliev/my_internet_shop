package mate.academy.internet.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.service.ItemService;
import mate.academy.internet.shop.service.OrderService;
import org.apache.log4j.Logger;

public class DeleteOrderController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteOrderController.class);
    @Inject
    private static OrderService orderService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("order_id");
        try {
            orderService.deleteById(Long.valueOf(orderId));
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/dataProcessingProblem.jsp")
                    .forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/getUserOrders");
    }
}
