package org.iesfm.shop;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.core.env.PropertySource;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private int clientId;
    private List<OrderItem> items;
    private Date date;

    public Order(int id, int clientId, Date date) {
        this.id = id;
        this.clientId = clientId;
        this.items = items;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && clientId == order.clientId && Objects.equals(items, order.items) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, items, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", clientId=" + clientId +
                ", items=" + items +
                ", date=" + date +
                '}';
    }
}
