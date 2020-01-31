package mate.academy.internet.shop.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.service.UserService;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {
    @Inject
    private static UserService userService;
    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Long userId = (Long) request.getSession().getAttribute("userId");
        try {
            if (userId == null || userService.get(userId) == null) {
                processUnAuthenticated(request, response);
                return;
            }
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            request.getRequestDispatcher("/WEB-INF/views/dataProcessingProblem.jsp")
                    .forward(request, response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void processUnAuthenticated(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
    }
}
