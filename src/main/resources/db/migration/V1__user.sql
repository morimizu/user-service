create table if not exists users
(
    id            int auto_increment primary key ,
    username      NVARCHAR2(200)  not null,
    password      NVARCHAR2(200) not null,
    email         NVARCHAR2(200) not null,
    creation_date TIMESTAMP
);
delete from users;
insert into
    users(username, password, email, creation_date)
    values ('testUser','password','testUser@test.com',current_timestamp());