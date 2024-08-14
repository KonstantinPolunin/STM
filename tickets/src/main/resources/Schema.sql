create schema stm

create table transporters
(
    id serial not null
        constraint transporter_pk
            primary key,
    name varchar not null,
    phone_number varchar not null
);

create table routs
(
    id serial not null
        constraint routs_pk
            primary key,
    departure varchar not null,
    destination varchar not null,
    duration integer not null,
    transporter_id bigint not null
        constraint routs_transporters_id_fk
            references transporters
);

create table users
(
    id serial not null
        constraint users_pk
            primary key,
    first_name varchar not null,
    last_name varchar not null,
    login varchar not null,
    password varchar not null
);

create table tickets
(
    id serial not null
        constraint tickets_pk
            primary key,
    rout_id integer not null
        constraint tickets_routs_id_fk
            references routs,
    time timestamp not null,
    seat integer not null,
    price numeric not null,
    is_bought boolean default false,
    user_id bigint
        constraint tickets_users_id_fk
            references users
);

create table roles
(
    id serial
        constraint roles_pk
            primary key,
    role varchar
);

create table user_roles
(
    id serial
        constraint user_roles_pk
            primary key,
    user_id bigint
        constraint user_roles_users_id_fk
            references users,
    role_id bigint
        constraint user_roles_roles_id_fk
            references roles
);
