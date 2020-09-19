create table staff (
       personnel_number int8 not null,
        date_of_birth date not null,
        gender varchar(11) not null,
        home_address varchar(255) not null,
        name varchar(50) not null,
        passport varchar(255) not null,
        patronymic varchar(100),
        position varchar(100) not null,
        salary numeric(19, 2) not null,
        surname varchar(100),
        primary key (personnel_number)
    );
