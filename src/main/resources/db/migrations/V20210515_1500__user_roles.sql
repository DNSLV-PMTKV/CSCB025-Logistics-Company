create table if not exists roles (
    id BIGSERIAL not null PRIMARY KEY,
    name varchar(128) not null
);
create table if not exists users_roles (
    roles_id BIGINT references roles(id),
    user_id BIGINT references users(id)
);
insert into roles (name)
values ('ROLE_COURIER'),
    ('ROLE_OFFICE'),
    ('ROLE_CLIENT'),
    ('ROLE_ADMIN');