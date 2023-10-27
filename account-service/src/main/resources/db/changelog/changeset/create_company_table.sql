create table if not exists companies(
    id      uuid            primary key not null default gen_random_uuid(),
    name    varchar(255)    not null,
    admin   varchar(255)    not null
);