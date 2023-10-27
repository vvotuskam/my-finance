create table if not exists transactions(
    id              uuid            primary key not null default gen_random_uuid(),
    transferred_at  timestamp       not null default current_timestamp,
    email           varchar(255)    not null,
    amount          float           not null check ( amount >= 100 ),
    account_id      uuid            not null,
    second_id       uuid            not null,
    is_positive     boolean         not null,
    description     varchar(255)    not null
)