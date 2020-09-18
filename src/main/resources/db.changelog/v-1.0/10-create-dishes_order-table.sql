create table dishes_order (
       order_id int8 not null,
        dish_id int8 not null
    )

    GO

alter table if exists dishes_order
       add constraint fk_dish
       foreign key (dish_id)
       references dishes

GO

alter table if exists dishes_order
       add constraint fk_order
       foreign key (order_id)
       references orders

       GO
