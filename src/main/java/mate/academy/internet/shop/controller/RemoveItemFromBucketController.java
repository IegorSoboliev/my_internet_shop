package mate.academy.internet.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.service.BucketService;
import mate.academy.internet.shop.service.ItemService;
import org.apache.log4j.Logger;

public class RemoveItemFromBucketController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RemoveItemFromBucketController.class);
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        Bucket bucket = null;
        try {
            bucket = bucketService.getByUserId(userId);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/dataProcessingProblem.jsp")
                    .forward(req, resp);
        }
        String itemId = req.getParameter("item_id");
        Item item = null;
        try {
            item = itemService.get(Long.valueOf(itemId));
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/dataProcessingProblem.jsp")
                    .forward(req, resp);
        }
        try {
            bucketService.deleteItem(bucket, item);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/dataProcessingProblem.jsp")
                    .forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/bucket");
    }
}
