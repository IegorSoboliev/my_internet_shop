package mate.academy.internet.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.service.ItemService;
import org.apache.log4j.Logger;

public class AddItemController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddItemController.class);
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item item = new Item();
        item.setName(req.getParameter("item_name"));
        String price = req.getParameter("item_price");
        item.setPrice(Integer.valueOf(price));
        try {
            itemService.create(item);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/dataProcessingProblem.jsp")
                    .forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/getAllItems");
    }
}
