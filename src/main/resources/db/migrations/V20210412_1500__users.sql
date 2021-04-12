create table if not exists users (
    id BIGSERIAL not null PRIMARY KEY,
    username varchar(128) not null,
    email varchar(128) not null,
    password varchar(256) not null,
    created_ts timestamp with time zone not null default now(),
    updated_ts timestamp with time zone default now()
);