package org.iesfm.shop.dao.jdbc;

import org.iesfm.shop.Client;
import org.iesfm.shop.Order;
import org.iesfm.shop.dao.ClientDAO;
import org.iesfm.shop.dao.OrderDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBOrderDAO implements OrderDAO {
    private static String SELECT_ORDER = "SELECT * FROM Shop_Order";
    private static String SELECT_CLIENTID_ORDERS = "SELECT * Shop_Order WHERE client_id = :client_id";
    private static String SELECT_ORDER_BY_ORDERID = "SELECT * FROM Shop_Order WHERE id = :id";
    private static String INSERT_ORDER = "INSERT INTO Shop_Order(" +
            "   id, " +
            "   client_id, " +
            "   order_date " +
            ") " +
            "VALUES(" +
            "   :id," +
            "   :client_id," +
            "   :order_date" +
            ")";
    private final static String DELETE_ORDER = "DELETE * FROM Shop_Order WHERE id = :id";
    private final static String UPDATE_ORDER = "UPDATE * FROM Shop_Order";

    private static final RowMapper<Order> ORDER_ROW_MAPPER =
            (rs, rownum) ->
                    new Order(
                            rs.getInt("id"),
                            rs.getInt("client_id"),
                            rs.getDate("order_date")
                    );

    private NamedParameterJdbcTemplate jdbc;

    public JDBOrderDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Order> list() {
        Map<String, Object> params = new HashMap<>();
        return jdbc.query(
                SELECT_ORDER,
                params,
                ORDER_ROW_MAPPER
        );
    }

    @Override
    public List<Order> list(int clientId) {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", clientId);
        return jdbc.query(
                SELECT_CLIENTID_ORDERS,
                params,
                ORDER_ROW_MAPPER
        );
    }

    @Override
    public Order get(int orderId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("order_id", orderId);
            return jdbc.queryForObject(
                    SELECT_ORDER_BY_ORDERID,
                    params,
                    ORDER_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean insert(Order order) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", order.getId());
        params.put("client_id", order.getClientId());
        params.put("order_date", order.getDate());
        jdbc.update(INSERT_ORDER, params);
        return false;
    }

    @Override
    public boolean update(Order order) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", order.getId());
        params.put("client_id", order.getClientId());
        params.put("order_date", order.getDate());
        jdbc.update(UPDATE_ORDER, params);
        if (order.equals(order.getId())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(int orderId) {
        Order order = null;
        Map<String, Object> params = new HashMap<>();
        params.put("id", order.getId());
        params.put("client_id", order.getClientId());
        params.put("order_date", order.getDate());

        jdbc.delete(DELETE_ORDER, params);
        return true;
    }
}
