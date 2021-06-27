create table if not exists shipments (
    id BIGSERIAL not null primary key,
    sender_id BIGINT references users(id) not null,
    receiver_id BIGINT references users(id) not null,
    address VARCHAR(256) not null,
    weight REAL not null,
    delivered BOOLEAN not null default 'false',
    created_ts timestamp with time zone not null default now(),
    updated_ts timestamp with time zone default now()
);