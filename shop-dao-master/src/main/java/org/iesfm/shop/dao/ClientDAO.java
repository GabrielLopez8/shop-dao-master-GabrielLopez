package org.iesfm.shop.dao;

import org.iesfm.shop.Article;
import org.iesfm.shop.Client;

import java.util.List;

public interface ClientDAO {

    List<Client> list();

    Client get(int id);

    boolean insert(Client client);

    boolean update(Client client);

    boolean delete(int id);
}
