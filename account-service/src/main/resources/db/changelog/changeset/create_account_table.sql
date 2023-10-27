create table if not exists otp_codes(
    id          uuid            primary key not null default gen_random_uuid(),
    name        varchar(255)    not null,
    amount      float           not null,
    email       varchar(255)    not null
)