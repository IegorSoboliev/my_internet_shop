package mate.academy.internet.shop.lib;

import java.util.HashMap;
import java.util.Map;

import mate.academy.internet.shop.dao.BucketDao;
import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.factories.Factory;
import mate.academy.internet.shop.service.BucketService;
import mate.academy.internet.shop.service.ItemService;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.UserService;

public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(UserDao.class, Factory.getInstanceUserDao());
        classMap.put(BucketDao.class, Factory.getInstanceBucketDao());
        classMap.put(ItemDao.class, Factory.getInstanceItemDao());
        classMap.put(OrderDao.class, Factory.getInstanceOrderDao());
        classMap.put(UserService.class, Factory.getInstanceUserService());
        classMap.put(BucketService.class, Factory.getInstanceBucketService());
        classMap.put(ItemService.class, Factory.getInstanceItemService());
        classMap.put(OrderService.class, Factory.getInstanceOrderService());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
