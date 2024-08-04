create table if not exists battery (
    id uuid NOT NULL DEFAULT random_uuid() primary key,
    version integer,
    name varchar(20) NOT NULL,
    post_code int,
    watt_capacity double
);