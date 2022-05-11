CREATE TABLE IF NOT EXISTS employee (
  id bigint NOT NULL AUTO_INCREMENT,
  first_name varchar(20) NOT NULL,
  last_name varchar(20) NOT NULL,
  age tinyInt NOT NULL,
  PRIMARY KEY (id)
);
