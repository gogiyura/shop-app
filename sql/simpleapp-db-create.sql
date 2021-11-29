-- Create table
create table USER_ACCOUNT
(
    USER_NAME VARCHAR(30) not null,
    GENDER    VARCHAR(1) not null,
    PASSWORD  VARCHAR(30) not null,
    primary key (USER_NAME),
    ROLE VARCHAR(15) not null DEFAULT 'USER',
    isblocked BOOL not null DEFAULT false

);

-- Create table
create table PRODUCT
(
    CODE  VARCHAR(20) not null,
    NAME  VARCHAR(128) not null,
    PRICE FLOAT not null,
    primary key (CODE)
) ;

-- Insert data: ---------------------------------------------------------------

insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('tom', 'M', 'tom001');

insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('jerry', 'M', 'jerry001');

insert into user_account (USER_NAME, GENDER, PASSWORD, ROLE)
values ('admin', 'W', 'admin', 'ADMIN');

insert into product (CODE, NAME, PRICE)
values ('P001', 'Java Core', 100);

insert into product (CODE, NAME, PRICE)
values ('P002', 'C# Core', 90);

