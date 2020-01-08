package mate.academy.internet.shop.lib;

import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.dao.impl.BucketDaoImpl;
import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.BucketService;
import mate.academy.internet.shop.service.ItemService;
import mate.academy.internet.shop.service.OrderService;
import mate.academy.internet.shop.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class TestMain {
    @Inject
    public static ItemService itemService;
    @Inject
    public static ItemDao itemDao;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Item apple = new Item("apple", 18.50);
        User first = new User("Bogdan", "b.chupilka@gmail.com");
        Bucket bucketFirst = new Bucket(first);
        List<Item> emptyList = new ArrayList<>();
        Order orderFirst = new Order(first, emptyList);
        itemService.create(apple);
        System.out.println(Storage.items);

    }
}