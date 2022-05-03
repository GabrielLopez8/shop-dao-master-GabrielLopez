package org.iesfm.shop;

import org.iesfm.shop.dao.ArticleDAO;
import org.iesfm.shop.dao.ClientDAO;
import org.iesfm.shop.dao.OrderDAO;
import org.iesfm.shop.dao.jdbc.JDBCArticleDAO;
import org.iesfm.shop.dao.jdbc.JDBCClientDAO;
import org.iesfm.shop.dao.jdbc.JDBOrderDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        DAOConfiguration.class
                );
        ArticleDAO articleProgram = context.getBean(JDBCArticleDAO.class);

        Article article1 = new Article(34561, "Animales", 9);
        Article article2 = new Article(98373, "Historia", 10);

        List<Article> articles = articleProgram.get();
        for (Article article : articles) {
            System.out.println(article.toString());
        }

        ClientDAO clientProgram = context.getBean(JDBCClientDAO.class);
        Client client1 = new Client(3445, "Andrés", "Villa");
        Client client2 = new Client(1720, "Elena", "Garcia");

        List<Client> clients = clientProgram.list();
        for (Client client : clients) {
            System.out.println(client.toString());
        }

        OrderDAO orderProgram = context.getBean(JDBOrderDAO.class);
        Order order1 = new Order(3445, 23466, new Date(1 / 2020));
        Order order2 = new Order(1720, 65578, new Date(3 / 11 / 2020));

        boolean orders = orderProgram.update(order2);
        System.out.println(order2);
    }
}

