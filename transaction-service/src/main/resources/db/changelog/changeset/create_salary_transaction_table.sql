create table if not exists salary_transactions(
    id             uuid             primary key not null default gen_random_uuid(),
    transferred_at timestamp        not null default current_timestamp,
    email          varchar(255)     not null,
    amount         float            not null check ( amount > 0 ),
    account_id     uuid             not null,
    company_id     uuid             not null,
    admin          varchar(255)     not null
)