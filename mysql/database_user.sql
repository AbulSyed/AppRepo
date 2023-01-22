create database user;
use user;

create table user (
  id bigint not null auto_increment,
  avatar_url varchar(255),
  github_url varchar(255),
  repo_url varchar(255),
  token varchar(255),
  username varchar(255),
  primary key (id)
) engine=InnoDB