package org.iesfm.shop;

import java.util.Objects;

public class OrderItem {
    private int articleId;
    private int amount;

    public OrderItem(int articleId, int amount) {
        this.articleId = articleId;
        this.amount = amount;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return articleId == orderItem.articleId && amount == orderItem.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, amount);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "articleId=" + articleId +
                ", amount=" + amount +
                '}';
    }
}
