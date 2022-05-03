package org.iesfm.shop.dao;

import org.iesfm.shop.Article;

import java.util.List;

public interface ArticleDAO {

    List<Article> list();

    List<Article> list(String tag);

    Article get(int id);

    boolean insert(Article article);

    boolean update(Article article);

    boolean delete(int id);
}
