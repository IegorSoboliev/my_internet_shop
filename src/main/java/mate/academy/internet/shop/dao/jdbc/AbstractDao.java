package mate.academy.internet.shop.dao.jdbc;

import java.sql.Connection;

public class AbstractDao<T> {
    protected final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}