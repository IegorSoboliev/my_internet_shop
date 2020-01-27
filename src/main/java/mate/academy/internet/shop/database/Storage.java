package mate.academy.internet.shop.database;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;

public class Storage {
    public static final List<Item> items = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
}
