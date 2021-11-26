show databases;
use shop;

drop table if exists users;
create table users(
    id int not null auto_increment;
    name varchar(20);
    email varchar(64) unique ;
    password varchar(32);
    phoneNumber VARCHAR(13);
    role enum ("user", "admin");
    blocked;

)