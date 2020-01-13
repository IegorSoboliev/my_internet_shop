package mate.academy.internet.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.service.ItemService;

public class AddItemController extends HttpServlet {
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
        itemService.create(item);
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
