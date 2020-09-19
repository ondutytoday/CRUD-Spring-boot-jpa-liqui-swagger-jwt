alter table if exists dishes_order
       drop constraint if exists fk_dish;



alter table if exists dishes_order
       drop constraint if exists fk_order;


drop table if exists dishes_order cascade;

