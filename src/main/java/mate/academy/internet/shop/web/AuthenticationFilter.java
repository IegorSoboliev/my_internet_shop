package mate.academy.internet.shop.web;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {
    @Inject
    private static UserService userService;
    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getCookies() == null) {
            processUnauthenticated(request, response);
            return;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                Optional<User> user = userService.findByToken(cookie.getValue());
                if (user.isPresent()) {
                    LOGGER.info("User " + user.get().getEmail() + "was authenticated");
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        LOGGER.info("User was not authenticated");
        processUnauthenticated(request, response);
    }

    private void processUnauthenticated(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
    }
}
