create schema stm;

create sequence users_2_id_seq
	as integer;

create table if not exists transporters
(
	id integer not null
		constraint transporter_pk
			primary key,
	name varchar not null,
	phone_number varchar not null
);

create table if not exists routs
(
	id integer default nextval('stm.routs'::regclass) not null
		constraint routs_pk
			primary key,
	departure varchar not null,
	destination varchar not null,
	duration integer not null,
	transporter_id bigint not null
		constraint routs_transporters_id_fk
			references transporters
);

create table if not exists roles
(
	id serial
		constraint roles_pk
			primary key,
	name varchar
);

create table if not exists users
(
	id integer default nextval('stm.users_2_id_seq'::regclass) not null
		constraint users_pk
			primary key,
	first_name varchar not null,
	last_name varchar not null,
	login varchar not null,
	password varchar not null,
	role_id bigint
		constraint users_roles_id_fk
			references roles
);

alter sequence users_2_id_seq owned by users.id;

create table if not exists tickets
(
	id integer default nextval('stm.tickets'::regclass) not null
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


