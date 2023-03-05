-- 1-  (Re)Create tables
drop table users if exists;
drop table books if exists;

create table users
(
    user_id          int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name        TEXT,
    user_permissions TEXT
);

create table books
(
    id             int  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name           TEXT NOT NULL,
    description    TEXT,
    user_id        int,
    uploaded_by_id int  NOT NULL
);


-- 2- Insert data into tables
-- users					
insert into users(user_name, user_permissions)
values ('Selina', 'read');
insert into users(user_name, user_permissions)
values ('Jannah', 'write');
insert into users(user_name, user_permissions)
values ('Dennis', 'read;write;admin');
insert into users(user_name, user_permissions)
values ('Thomas', '');

-- books
insert into books(name, description, uploaded_by_id, user_id)
values ('1Q84', 'Nice book', 2, 1);
insert into books(name, description, uploaded_by_id)
values ('2066', 'Interesting one, I guess?', 3);
insert into books(name, description, uploaded_by_id)
values ('Bolt', 'by Dick Francis', 2);
insert into books(name, description, uploaded_by_id)
values ('Cuyo', 'scary one!', 2);
