package mate.academy.internet.shop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.BucketService;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.UserService;

public class CompleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;
    private static final Long USER_ID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bucketId = req.getParameter("bucket_id");
        Bucket bucket = bucketService.get(Long.valueOf(bucketId));
        List<Item> items = bucket.getItems();
        User user = userService.get(USER_ID);
        orderService.completeOrder(items, user);
        resp.sendRedirect(req.getContextPath() + "/servlet/orders");
    }
}
