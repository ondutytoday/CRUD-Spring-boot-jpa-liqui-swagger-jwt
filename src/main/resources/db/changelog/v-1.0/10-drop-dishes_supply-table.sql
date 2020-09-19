alter table if exists dishes_supply
       drop constraint if exists FK_dish;

alter table if exists dishes_supply
       drop constraint if exists FK_supply;

drop table if exists dishes_supply cascade;

