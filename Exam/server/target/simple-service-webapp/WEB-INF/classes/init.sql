DROP DATABASE IF EXISTS dis_exam ;
CREATE DATABASE dis_exam;
Use dis_exam;

CREATE TABLE IF NOT EXISTS customers (
  customer_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL,
  PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS accounts (
  account_id INT NOT NULL AUTO_INCREMENT,
  customer_id INT NOT NULL,
  balance INT NOT NULL,
  PRIMARY KEY (account_id),
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

INSERT INTO customers(name) VALUES ("Philip Kaare Løventoft");
INSERT INTO accounts(customer_id, balance) VALUES (1, 1000)