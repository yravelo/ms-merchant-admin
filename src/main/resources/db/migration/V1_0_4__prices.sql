create table prices(
   id                bigint auto_increment primary key,
   brand_id          bigint           not null,
   product_id        bigint           not null,
   price_list_id     bigint           not null,
   priority          int              not null default 0,
   currency_iso_code int              not null,
   price             decimal(14, 2)   not null,
   discount          decimal(4, 2)   not null,
   tax               decimal(4, 2)   not null,
   start_date        datetime         not null,
   end_date          datetime         not null,
   create_date_time  datetime         not null,
   update_date_time  datetime         not null,
   constraint fk_prices_brand_id      foreign key (brand_id)   references brand (id),
   constraint fk_prices_product_id    foreign key (product_id) references product (id),
   constraint fk_prices_price_list_id foreign key (price_list_id) references price_list (id)
);