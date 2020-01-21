package mate.academy.internet.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Injector;
import mate.academy.internet.shop.service.ItemService;

public class IndexController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        itemService.getAll();
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
