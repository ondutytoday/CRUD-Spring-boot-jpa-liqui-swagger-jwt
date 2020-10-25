create table roles
(
	role_id bigserial not null,
	role varchar(50) not null,
	primary key (role_id)
);

create unique index roles_role_id_uindex
	on roles (role_id);

create unique index roles_role_uindex
	on roles (role);

insert into roles(role) values ('ROLE_ADMIN');
insert into roles(role) values ('ROLE_USER');