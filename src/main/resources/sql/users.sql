-- 
-- docker exec -it postgres psql -u postgres -w


create database ss_default_jdbc;
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);


INSERT  INTO users VALUES ('user', '{noop}password', '1');
INSERT  INTO authorities VALUES ('user', 'read');

INSERT  INTO users VALUES ('admin', '{bcrypt}$2a$12$oJlx823VQvE7u7nB/v3Z9ONWQ9Q2D.Zg/smpGb3TgqG2UM1P9zhVq', '1');
INSERT  INTO authorities VALUES ('admin', 'admin');



-- custom user table creation customer
CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY, -- Matches GenerationType.IDENTITY and long id
    email VARCHAR(255),
    pwd VARCHAR(255),
    role VARCHAR(255)
);

INSERT  INTO customer ( email, pwd, role) VALUES ('user@example.com', '{noop}password', 'read');
INSERT  INTO customer ( email, pwd, role) VALUES ('admin@example.com', '{bcrypt}{bcrypt}$2a$12$oJlx823VQvE7u7nB/v3Z9ONWQ9Q2D.Zg/smpGb3TgqG2UM1P9zhVq', 'admin');
