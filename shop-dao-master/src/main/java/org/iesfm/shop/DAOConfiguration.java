package org.iesfm.shop;

import org.iesfm.shop.dao.ArticleDAO;
import org.iesfm.shop.dao.ClientDAO;
import org.iesfm.shop.dao.OrderDAO;
import org.iesfm.shop.dao.jdbc.JDBCArticleDAO;
import org.iesfm.shop.dao.jdbc.JDBCClientDAO;
import org.iesfm.shop.dao.jdbc.JDBOrderDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("application.properties")
public class DAOConfiguration {

    @Bean
    public ArticleDAO articleDAO(NamedParameterJdbcTemplate jdbc) {
        return new JDBCArticleDAO(jdbc);
    }

    @Bean
    public ClientDAO clientDAO(NamedParameterJdbcTemplate jdbc) {
        return new JDBCClientDAO(jdbc);
    }

    @Bean
    public OrderDAO orderDAO(NamedParameterJdbcTemplate jdbc) {
        return new JDBOrderDAO(jdbc);
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource(
            // placeholder
            @Value("${database.url}") String url,
            @Value("${database.user}") String user,
            @Value("${database.password}") String password
    ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
