create table person (
                        id bigserial primary key,
                        first_name varchar(50) not null,
                        middle_name varchar(100),
                        last_name varchar(50) not null,
                        email varchar(100),
                        passport_series varchar(10),
                        passport_number varchar(20),
                        inn varchar(20),
                        snils varchar(20),
                        phone_number varchar(20)
);

create table work (
                      id bigserial not null primary key unique,
                      company_name varchar(100) not null,
                      inn varchar(20) not null,
                      person_id bigint not null references person(id),
                      start_work date not null,
                      end_work date,
                      position varchar(100) not null
);

insert into person (first_name, middle_name, last_name, email, passport_series, passport_number, inn, snils, phone_number)
values ('Ivan', 'Petrovich', 'Sinichkin', 'sinichkin.ivan@gmail.com', '12 34', '123 456', '012345678901', '123-123-123-23', '79611234567');

insert into work (company_name, inn, person_id, start_work, end_work, position)
values ('Yandex', '0123456789', 1, now(), now(), 'Java software engineer');

insert into work (company_name, inn, person_id, start_work, end_work, position)
values ('Google', '0123456789', 1, now(), now(), 'Middle Python engineer');