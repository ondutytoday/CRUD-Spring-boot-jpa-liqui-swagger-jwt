create table supply (
       supply_id int8 not null,
        date_of_supply timestamp not null,
        supplier_id int8,
        primary key (supply_id)
    );



alter table if exists supply
       add constraint FK_supplier
       foreign key (supplier_id)
       references suppliers;

