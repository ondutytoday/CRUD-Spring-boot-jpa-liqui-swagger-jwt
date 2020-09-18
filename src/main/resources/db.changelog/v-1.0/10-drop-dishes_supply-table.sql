alter table if exists dishes_supply
       drop constraint if exists FK_dish
GO
alter table if exists dishes_supply
       drop constraint if exists FK_supply
GO
drop table if exists dishes_supply cascade

GO