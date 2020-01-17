package mate.academy.internet.shop.web;

import static mate.academy.internet.shop.model.Role.RoleName.ADMIN;
import static mate.academy.internet.shop.model.Role.RoleName.USER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Role;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;

public class AuthorizationFilter implements Filter {
    @Inject
    private static UserService userService;
    private Map<String, Role.RoleName> onlyAdminUrls = new HashMap<>();
    private Map<String, Role.RoleName> onlyUserUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        onlyAdminUrls.put("/servlet/getAllUsers", ADMIN);
        onlyAdminUrls.put("/servlet/addItem", ADMIN);
        onlyAdminUrls.put("/servlet/deleteUser", ADMIN);
        onlyAdminUrls.put("/servlet/logout", ADMIN);

        onlyUserUrls.put("/servlet/deleteOrder", USER);
        onlyUserUrls.put("/servlet/getUserOrders", USER);
        onlyUserUrls.put("/servlet/completeOrder", USER);
        onlyUserUrls.put("/servlet/removeItemFromBucket", USER);
        onlyUserUrls.put("/servlet/bucket", USER);
        onlyUserUrls.put("/servlet/addItemToBucket", USER);
        onlyUserUrls.put("/servlet/logout", USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Role.RoleName rolesAdmin = onlyAdminUrls.get(request.getServletPath());
        Role.RoleName rolesUser = onlyUserUrls.get(request.getServletPath());
        if (rolesAdmin == null && rolesUser == null) {
            processAuthorized(filterChain, request, response);
            return;
        }

        Long userId = (Long) request.getSession().getAttribute("userId");
        User user = userService.get(userId);
        if (verifyRole(user, rolesAdmin) || verifyRole(user, rolesUser)) {
            processAuthorized(filterChain, request, response);
            return;
        } else {
            processDenied(request, response);
            return;
        }
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        return user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(roleName));
    }

    private void processAuthorized(FilterChain filterChain,
                                   HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        filterChain.doFilter(request, response);
    }

    private void processDenied(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
    }
}

