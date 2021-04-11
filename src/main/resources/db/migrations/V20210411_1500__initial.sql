create table if not exists companies (
    id BIGSERIAL not null PRIMARY KEY,
    name varchar(256) not null,
    created_ts timestamp with time zone not null default now(),
    updated_ts timestamp with time zone default now()
);