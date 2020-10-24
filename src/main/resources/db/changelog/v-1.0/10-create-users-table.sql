create table table_name
(
	user_id bigserial not null,
	username varchar(100) not null,
	password varchar(255) not null,
	email varchar(255) not null,
	role varchar(15) not null
);

create unique index users_email_uindex
	on table_name (email);

create unique index users_user_id_uindex
	on table_name (user_id);

create unique index users_username_uindex
	on table_name (username);

alter table table_name
	add constraint users_pk
		primary key (user_id);