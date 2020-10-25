create table users
(
	user_id bigserial not null,
	username varchar(100) not null,
	password varchar(255) not null,
	email varchar(255) not null,
	role_id int8 not null,
	primary key (user_id)
);

create unique index users_email_uindex
	on users (email);

create unique index users_user_id_uindex
	on users (user_id);

create unique index users_username_uindex
	on users (username);

alter table if exists users
       add constraint FK_ROLE
       foreign key (role_id)
       references roles;