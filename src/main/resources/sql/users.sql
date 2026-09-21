create database ss_default_jdbc;
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);


INSERT  INTO users VALUES ('user', '{noop}password', '1');
INSERT  INTO authorities VALUES ('user', 'read');

INSERT  INTO users VALUES ('admin', '{bcrypt}$2a$12$oJlx823VQvE7u7nB/v3Z9ONWQ9Q2D.Zg/smpGb3TgqG2UM1P9zhVq', '1');
INSERT  INTO authorities VALUES ('admin', 'admin');
