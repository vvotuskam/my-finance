create table if not exists employees(
    id          uuid            primary key not null default gen_random_uuid(),
    name        varchar(255)    not null,
    surname     varchar(255)    not null,
    salary      float           not null check ( salary > 0 ),
    email       varchar(255)    not null,
    account_id  uuid            not null references accounts(id),
    company_id  uuid            not null references companies(id)
);