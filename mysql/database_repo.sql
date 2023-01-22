create database repo;
use repo;

create table repo (
  id bigint not null auto_increment,
  category varchar(255),
  clone_url varchar(255),
  description varchar(255),
  html_url varchar(255),
  language varchar(255),
  name varchar(255),
  primary key (id)
) engine=InnoDB