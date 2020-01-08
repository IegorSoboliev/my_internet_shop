package mate.academy.internet.shop.factories;

import mate.academy.internet.shop.dao.BucketDao;
import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.dao.impl.BucketDaoImpl;
import mate.academy.internet.shop.dao.impl.ItemDaoImpl;
import mate.academy.internet.shop.dao.impl.OrderDaoImpl;
import mate.academy.internet.shop.dao.impl.UserDaoImpl;
import mate.academy.internet.shop.service.BucketService;
import mate.academy.internet.shop.service.ItemService;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.UserService;
import mate.academy.internet.shop.service.impl.BucketServiceImpl;
import mate.academy.internet.shop.service.impl.ItemServiceImpl;
import mate.academy.internet.shop.service.impl.OrderServiceImpl;
import mate.academy.internet.shop.service.impl.UserServiceImpl;

public class Factory {
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
            instanceItemDao = new ItemDaoImpl();
        }
        return instanceItemDao;
    }

    public static UserDao getInstanceUserDao() {
        if (instanceUserDao == null) {
            instanceUserDao = new UserDaoImpl();
        }
        return instanceUserDao;
    }

    public static BucketDao getInstanceBucketDao() {
        if (instanceBucketDao == null) {
            instanceBucketDao = new BucketDaoImpl();
        }
        return instanceBucketDao;
    }

    public static OrderDao getInstanceOrderDao() {
        if (instanceOrderDao == null) {
            instanceOrderDao = new OrderDaoImpl();
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

