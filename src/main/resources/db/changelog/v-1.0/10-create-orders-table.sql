create table orders (
       order_id int8 not null,
        payment_method varchar(4) not null,
        timestamp timestamp not null,
        personnel_number int8,
        primary key (order_id)
    );

alter table if exists orders
       add constraint FK_STAFF
       foreign key (personnel_number)
       references staff;

