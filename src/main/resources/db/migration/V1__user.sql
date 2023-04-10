create table if not exists users
(
    id            int auto_increment primary key ,
    username      NVARCHAR2(200)  not null,
    password      NVARCHAR2(200) not null,
    email         NVARCHAR2(200) not null,
    creation_date TIMESTAMP
);