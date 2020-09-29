 alter table if exists orders
       drop constraint if exists FK_STAFF;

 drop table if exists orders cascade;
