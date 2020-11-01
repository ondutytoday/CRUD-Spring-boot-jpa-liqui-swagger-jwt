delete from users;
delete from roles;

insert into roles(role_id, role) values (1, 'admin');
insert into roles(role_id, role) values (2, 'user');

insert into users (user_id, username, password, email, role_id) values (1, 'admin', '$2y$12$Ye8E6QSYZfRolFkLfrif5eRH93fA4WIpZ5RELYvbULkip8wTifDe2', 'cbooeln0@devhub.com', 1);
insert into users (user_id, username, password, email, role_id) values (2, 'user1', '$2y$12$Ye8E6QSYZfRolFkLfrif5eRH93fA4WIpZ5RELYvbULkip8wTifDe2', 'pivanchikov1@nbcnews.com', 2);
insert into users (user_id, username, password, email, role_id) values (3, 'user2', '$2y$12$Ye8E6QSYZfRolFkLfrif5eRH93fA4WIpZ5RELYvbULkip8wTifDe2', 'siwanczyk2@geocities.jp', 2);
