create table product(
    id   bigint auto_increment primary key,
    code varchar(50)              not null,
    name varchar(100)             not null,
    description varchar(500)      null,
    standard_price decimal(14, 2)   not null,
    create_date_time datetime     not null,
    update_date_time datetime     not null,
    constraint product_code_ux unique (code)
);