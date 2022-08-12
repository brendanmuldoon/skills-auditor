CREATE TABLE app_user (
  id varchar(50) primary key,
  username varchar(50) unique,
  password varchar(255) not null,
  first_name varchar(40) not null,
  surname varchar(40) not null,
  role_id int not null
);

CREATE TABLE role (
  id int auto_increment primary key,
  type varchar(45) not null
);
