package org.iesfm.shop.dao;

import org.iesfm.shop.Article;
import org.iesfm.shop.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> list();

    List<Order> list(int clientId);

    Order get(int orderId);

    boolean insert(Order order);

    boolean update(Order order);

    boolean delete(int orderId);
}
