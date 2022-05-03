package org.iesfm.shop.dao.jdbc;

import org.iesfm.shop.Client;
import org.iesfm.shop.dao.ArticleDAO;
import org.iesfm.shop.dao.ClientDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCClientDAO implements ClientDAO {
    private static String SELECT_CLIENT = "SELECT * FROM Client";
    private static String SELECT_CLIENT_BY_ID = "SELECT * FROM Client WHERE id = :id";
    private static String INSERT_CLIENT = "INSERT INTO Client(" +
            "   id, " +
            "   name, " +
            "   surname " +
            ") " +
            "VALUES(" +
            "   :id," +
            "   :name," +
            "   :surname" +
            ")";

    private static final RowMapper<Client> CLIENT_ROW_MAPPER =
            (rs, rownum) ->
                    new Client(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname")
                    );

    private NamedParameterJdbcTemplate jdbc;

    public JDBCClientDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Client> list() {
        Map<String, Object> params = new HashMap<>();
        return jdbc.query(
                SELECT_CLIENT,
                params,
                CLIENT_ROW_MAPPER
        );
    }

    @Override
    public Client get(int id) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            return jdbc.queryForObject(SELECT_CLIENT_BY_ID, params, CLIENT_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean insert(Client client) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", client.getId());
        params.put("name", client.getName());
        params.put("surname", client.getSurname());
        jdbc.update(INSERT_CLIENT, params);
        return true;
    }

    @Override
    public boolean update(Client client) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
