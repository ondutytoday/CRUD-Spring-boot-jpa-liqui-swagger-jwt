create table suppliers (
       supplier_id int8 not null,
        email varchar(255) not null,
        information varchar(255),
        inn int4 not null,
        phone_number varchar(255) not null,
        supplier_address varchar(255) not null,
        supplier_name varchar(255) not null,
        primary key (supplier_id)
    )

    GO
