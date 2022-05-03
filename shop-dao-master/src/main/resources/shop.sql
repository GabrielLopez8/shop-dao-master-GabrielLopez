CREATE DATABASE IF NOT EXISTS Shop;
    
USE Shop;

CREATE TABLE IF NOT EXISTS Article (
	id	    INT AUTO_INCREMENT,
	name	VARCHAR (40) UNIQUE NOT NULL,
	price	DOUBLE NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Tag (
	article_id	    INT,
	name	VARCHAR (40),
    PRIMARY KEY (article_id, name),
    CONSTRAINT FK_TAG_ARTICLE
		FOREIGN KEY (article_id)
		REFERENCES Article (id)
		ON UPDATE CASCADE
    	ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Client (
	id	    INT AUTO_INCREMENT,
	name	VARCHAR(40) NOT NULL,
	surname	VARCHAR(40) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Shop_Order (
	id              INT AUTO_INCREMENT, 
	client_id	    INT,
	order_date	    DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_CLIENT_ORDER
		FOREIGN KEY (client_id)
		REFERENCES Client (id)
		ON UPDATE CASCADE
    	ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Item (
	order_id        INT, 
	article_id	    INT,
	amount	INT NOT NULL,

    PRIMARY KEY (order_id, article_id),
    CONSTRAINT FK_ORDER_ITEM
		FOREIGN KEY (order_id)
		REFERENCES Shop_Order (id)
		ON UPDATE CASCADE
    	ON DELETE CASCADE,
    CONSTRAINT FK_ARTICLE_ITEM
		FOREIGN KEY (article_id)
		REFERENCES Article (id)
		ON UPDATE CASCADE
    	ON DELETE CASCADE
);

# Insertamos un tornillo
INSERT INTO Article(id, name, price) VALUES(1, 'Tornillo largo', 2);
INSERT INTO Tag(article_id, name) VALUES(1, 'Ferretería');
INSERT INTO Tag(article_id, name) VALUES(1, 'Tornillo');

# Insertamos un osito de peluche
INSERT INTO Article(id, name, price) VALUES(2, 'Osito', 100);
INSERT INTO Tag(article_id, name) VALUES(2, 'Juguete');
INSERT INTO Tag(article_id, name) VALUES(2, 'Peluche');


INSERT INTO Client(id, name, surname) VALUES(1, 'Pepe', 'López');
INSERT INTO Client(id, name, surname) VALUES(2, 'Ana', 'Sánchez');

INSERT INTO Shop_Order(id, client_id, order_date) VALUES(1, 1, STR_TO_DATE('12-10-2021 00:00:00','%m-%d-%Y %H:%i:%s'));
INSERT INTO Item(order_id, article_id, amount) VALUES(1,1,5);
INSERT INTO Item(order_id, article_id, amount) VALUES(1,2,1);


INSERT INTO Shop_Order(id, client_id, order_date) VALUES(2, 2, STR_TO_DATE('12-01-2014 00:00:00','%m-%d-%Y %H:%i:%s'));
INSERT INTO Item(order_id, article_id, amount) VALUES(2,1,2);