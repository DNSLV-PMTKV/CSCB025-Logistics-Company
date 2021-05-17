create table if not exists offices (
    id BIGSERIAL not null PRIMARY KEY,
    city varchar(128) not null,
    address varchar(512) not null,
    company_id BIGINT references companies(id),
    created_ts timestamp with time zone not null default now(),
    updated_ts timestamp with time zone default now()
);