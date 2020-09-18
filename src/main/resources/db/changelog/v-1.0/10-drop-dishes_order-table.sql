alter table if exists dishes_order
       drop constraint if exists fk_dish

GO

alter table if exists dishes_order
       drop constraint if exists fk_order

GO

drop table if exists dishes_order cascade

GO