create table dishes_supply (
       dishes_supply_id int8 not null,
        price numeric(19, 2) not null,
        quantity int4 not null,
        dish_id int8,
        supply_id int8,
        primary key (dishes_supply_id)
    );

alter table if exists dishes_supply
       add constraint FK_dish
       foreign key (dish_id)
       references dishes;

alter table if exists dishes_supply
       add constraint FK_supply
       foreign key (supply_id)
       references supply;
