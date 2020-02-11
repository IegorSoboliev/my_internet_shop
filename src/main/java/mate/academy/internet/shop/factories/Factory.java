package mate.academy.internet.shop.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mate.academy.internet.shop.dao.BucketDao;
import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.dao.jdbc.BucketDaoJdbcImpl;
import mate.academy.internet.shop.dao.jdbc.ItemDaoJdbcImpl;
import mate.academy.internet.shop.dao.jdbc.OrderDaoJdbcImpl;
import mate.academy.internet.shop.dao.jdbc.UserDaoJdbcImpl;
import mate.academy.internet.shop.service.BucketService;
import mate.academy.internet.shop.service.ItemService;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.UserService;
import mate.academy.internet.shop.service.impl.BucketServiceImpl;
import mate.academy.internet.shop.service.impl.ItemServiceImpl;
import mate.academy.internet.shop.service.impl.OrderServiceImpl;
import mate.academy.internet.shop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class Factory {
    private static Logger logger = Logger.getLogger(Factory.class);
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/storage?user="
                    + "YOUR_USERNAME&password=YOUR_PASSWORD&serverTimezone=UTC"); //login here
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Cannot establish a connection to DB", e);
        }
    }

    private static ItemDao instanceItemDao;
    private static UserDao instanceUserDao;
    private static BucketDao instanceBucketDao;
    private static OrderDao instanceOrderDao;
    private static ItemService instanceItemService;
    private static UserService instanceUserService;
    private static BucketService instanceBucketService;
    private static OrderService instanceOrderService;

    public static ItemDao getInstanceItemDao() {
        if (instanceItemDao == null) {
            instanceItemDao = new ItemDaoJdbcImpl(connection);
        }
        return instanceItemDao;
    }

    public static UserDao getInstanceUserDao() {
        if (instanceUserDao == null) {
            instanceUserDao = new UserDaoJdbcImpl(connection);
        }
        return instanceUserDao;
    }

    public static BucketDao getInstanceBucketDao() {
        if (instanceBucketDao == null) {
            instanceBucketDao = new BucketDaoJdbcImpl(connection);
        }
        return instanceBucketDao;
    }

    public static OrderDao getInstanceOrderDao() {
        if (instanceOrderDao == null) {
            instanceOrderDao = new OrderDaoJdbcImpl(connection);
        }
        return instanceOrderDao;
    }

    public static ItemService getInstanceItemService() {
        if (instanceItemService == null) {
            instanceItemService = new ItemServiceImpl();
        }
        return instanceItemService;
    }

    public static UserService getInstanceUserService() {
        if (instanceUserService == null) {
            instanceUserService = new UserServiceImpl();
        }
        return instanceUserService;
    }

    public static BucketService getInstanceBucketService() {
        if (instanceBucketService == null) {
            instanceBucketService = new BucketServiceImpl();
        }
        return instanceBucketService;
    }

    public static OrderService getInstanceOrderService() {
        if (instanceOrderService == null) {
            instanceOrderService = new OrderServiceImpl();
        }
        return instanceOrderService;
    }
}
