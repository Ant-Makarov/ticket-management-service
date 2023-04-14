alter schema public rename to task;

create table if not exists task.bus_route (
	id bigint primary key,
	from_departure varchar(255) not null,
	to_departure varchar(255) not null,
	departure_time timestamp not null,
	route_cost numeric(7,2) not null check(route_cost > 200.0),
	seats_available smallint not null check(seats_available >= 0)
);

create table if not exists task.ticket (
	ticket_id uuid primary key default gen_random_uuid(),
	owner_name varchar(150) not null,
	route_id bigint references task.bus_route(id) on delete cascade,
	payment_id uuid not null,
	payment_status varchar(10) not null,
	created_at timestamp not null,
	updated_at timestamp
);
