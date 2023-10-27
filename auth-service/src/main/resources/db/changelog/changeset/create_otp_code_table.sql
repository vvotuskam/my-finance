create table if not exists otp_codes(
    id          uuid            primary key not null default gen_random_uuid(),
    code        varchar(255)    not null check ( length(code) > 0 ),
    status      varchar(255)    not null check ( status in ('ACTIVE', 'CONFIRMED', 'REVOKED') ),
    created_at  timestamp       not null default current_timestamp,
    expires_at  timestamp       not null,
    email       varchar(255)    not null
)