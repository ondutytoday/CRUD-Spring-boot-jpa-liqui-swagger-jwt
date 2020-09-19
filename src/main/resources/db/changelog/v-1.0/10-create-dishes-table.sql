create table dishes (
       dish_id int8 not null,
        balance int4 not null,
        calories float8 not null,
        dish_name varchar(100) not null,
        price numeric(19, 2) not null,
        primary key (dish_id)
    );
