insert into brand (name, create_date_time, update_date_time) values ('ZARA', now(), now());

insert into product (code, name, standard_price, create_date_time, update_date_time) values ( '123456789', 'ABRIGO CON LANA KAIA X ZARA', 33.55, now(), now());

insert into price_list (name, create_date_time, update_date_time) values ('Viernes Santo',                   now(), now());
insert into price_list (name, create_date_time, update_date_time) values ('Fiesta Nacional de España',       now(), now());
insert into price_list (name, create_date_time, update_date_time) values ('Asunción de Nuestra Señora',      now(), now());
insert into price_list (name, create_date_time, update_date_time) values ('Día de la Constitución Española', now(), now());

insert into prices (brand_id, product_id, price_list_id, priority, currency_iso_code, price, discount, tax ,start_date, end_date, create_date_time, update_date_time)
       values      (1, 1, 1, 0, 978, 35.50, 10.15, 2.50,  '2020-06-14 00:00:00', '2020-12-31 23:59:59' ,   now(), now());
insert into prices (brand_id, product_id, price_list_id, priority, currency_iso_code, price, discount, tax ,start_date, end_date, create_date_time, update_date_time)
       values      (1, 1, 2, 1, 978, 25.45, 10.15, 2.50,  '2020-06-14 15:00:00', '2020-06-14 18:30:00',   now(), now());
insert into prices (brand_id, product_id, price_list_id, priority, currency_iso_code, price, discount, tax ,start_date, end_date, create_date_time, update_date_time)
       values      (1, 1, 3, 1, 978, 30.50 , 10.15, 2.50, '2020-06-15 00:00:00', '2020-06-15 11:00:00',   now(), now());
insert into prices (brand_id, product_id, price_list_id, priority, currency_iso_code, price, discount, tax ,start_date, end_date, create_date_time, update_date_time)
       values      (1, 1, 4, 1, 978, 38.95, 10.15, 2.50,  '2020-06-15 16:00:00' , '2020-12-31 23:59:59',   now(), now());
