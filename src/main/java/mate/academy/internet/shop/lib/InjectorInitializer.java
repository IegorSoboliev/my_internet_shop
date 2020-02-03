package mate.academy.internet.shop.lib;

import java.util.Optional;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.exceptions.EmailAlreadyRegisteredException;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;
import org.apache.log4j.Logger;

public class InjectorInitializer implements ServletContextListener {
    public static final Logger LOGGER = Logger.getLogger(Injector.class);
    @Inject
    private static UserDao userDao;
    @Inject
    private static UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            LOGGER.error("Dependency injection has not completed");
            throw new RuntimeException(e);
        }
        try {
            Optional<User> defaultAdmin = userDao.findUserByEmail("admin@yahoo.com");
            Optional<User> defaultUser = userDao.findUserByEmail("user@yahoo.com");

            if (defaultAdmin.isEmpty()) {
                injectDefaultAdmin();
            }
            if (defaultUser.isEmpty()) {
                injectDefaultUser();
            }
        } catch (EmailAlreadyRegisteredException | DataProcessingException e) {
            LOGGER.error("Cannot add default admin");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    private void injectDefaultAdmin() throws EmailAlreadyRegisteredException,
            DataProcessingException {
        User admin = new User();
        admin.setName("Default");
        admin.setSurname("Admin");
        admin.setEmail("admin@yahoo.com");
        admin.setPassword("1");
        userService.create(admin);
        userDao.setAdminRole(admin);
    }

    private void injectDefaultUser() throws EmailAlreadyRegisteredException,
            DataProcessingException {
        User user = new User();
        user.setName("Default");
        user.setSurname("User");
        user.setEmail("user@yahoo.com");
        user.setPassword("2");
        userService.create(user);
    }
}
