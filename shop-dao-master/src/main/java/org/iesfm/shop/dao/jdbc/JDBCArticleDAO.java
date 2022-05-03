package org.iesfm.shop.dao.jdbc;

import org.iesfm.shop.Article;
import org.iesfm.shop.dao.ArticleDAO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;
import java.util.logging.Logger;

public class JDBCArticleDAO implements ArticleDAO {

    private final static String SELECT_ARTICLE = "SELECT * FROM article";
    private final static String SELECT_TAG_ARTICLES = "SELECT a.* FROM Article a INNER JOIN Tag t ON a.id = t.article_id WHERE article_id = :article_id";
    private final static String SELECT_ARTICLE_BY_ID = "SELECT * FROM Article WHERE id = :id";
    private final static String INSERT_ARTICLE = "INSERT INTO Article(" +
            "  id,  " +
            "  name,  " +
            "  price  " +
            ") " +
            "VALUES(" +
            "  :id," +
            "  :name," +
            "  :price" +
            ")";

    private static final RowMapper<Article> ARTICLE_ROW_MAPPER =
            (rs, rownum) ->
                    new Article(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price")

                    );

    private NamedParameterJdbcTemplate jdbc;

    public JDBCArticleDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Article> list() {
        Map<String, Object> params = new HashMap<>();
        return jdbc.query(SELECT_ARTICLE, params, ARTICLE_ROW_MAPPER);
    }

    @Override
    public List<Article> list(String tag) {
        Map<String, Object> params = new HashMap<>();
        params.put("tag", tag);
        return jdbc.query(SELECT_TAG_ARTICLES, params, ARTICLE_ROW_MAPPER);
    }

    @Override
    public Article get(int id) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return jdbc.queryForObject(SELECT_ARTICLE_BY_ID, params, ARTICLE_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean insert(Article article) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", article.getId());
        params.put("name", article.getName());
        params.put("price", article.getPrice());
        jdbc.update(INSERT_ARTICLE, params);
        return true;
    }

    @Override
    public boolean update(Article article) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
