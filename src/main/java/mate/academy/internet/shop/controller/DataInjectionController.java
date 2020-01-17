package mate.academy.internet.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.Role;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.ItemService;
import mate.academy.internet.shop.service.UserService;

public class DataInjectionController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Item item = new Item();
        item.setName("Christmas carol");
        item.setPrice(125);
        itemService.create(item);

        User user = new User();
        user.setName("Masha");
        user.setSurname("Sobolieva");
        user.setEmail("masha@yahoo.com");
        user.setPassword("1");
        user.addRole(Role.of("USER"));
        userService.create(user);

        User admin = new User();
        admin.setName("Iegor");
        admin.setSurname("Soboliev");
        admin.setEmail("iegor@yahoo.com");
        admin.setPassword("1");
        admin.addRole(Role.of("ADMIN"));
        userService.create(admin);
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
