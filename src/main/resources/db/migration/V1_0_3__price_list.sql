create table price_list(
   id   bigint auto_increment primary key,
   name varchar(100)             not null,
   create_date_time datetime     not null,
   update_date_time datetime     not null,
   constraint price_list_name_ux unique (name)
);