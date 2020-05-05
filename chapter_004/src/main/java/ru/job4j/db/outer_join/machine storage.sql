CREATE DATABASE car_catalog;

create table car_body(
  id SERIAL PRIMARY KEY,
  name_body VARCHAR(200)
);

insert into car_body (name_body) values('e60');
insert into car_body (name_body) values('w212');
insert into car_body (name_body) values('2106');
insert into car_body (name_body) values('toyota coupe');
insert into car_body (name_body) values('bmw m5 f10');

create table engine(
  id SERIAL PRIMARY KEY,
  name_engine VARCHAR(200)
);
insert into engine (name_engine) values('m50');
insert into engine (name_engine) values('m271');
insert into engine (name_engine) values('vaz');
insert into engine (name_engine) values('2jz-gte');
insert into engine (name_engine) values('yamaha');
insert into engine (name_engine) values('umz-76');

create table transmission(
  id SERIAL PRIMARY KEY,
  name_trans VARCHAR(200)
);
insert into engine (name_engine) values('auto');
insert into engine (name_engine) values('auto');
insert into engine (name_engine) values('manual');
insert into engine (name_engine) values('manual');

create table car (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200),
  car_body_id INT  referenses car_body(id),
  car_engine_id INT referenses engine(id),
  car_transmission_id INT referenses transmission(id)
);
insert into car (name, car_body_id, car_engine_id, car_transmission_id) values ('bmw', 1, 1, 1);
insert into car (name, car_body_id, car_engine_id, car_transmission_id) values ('mercedes', 2, 2, 2);
insert into car (name, car_body_id, car_engine_id, car_transmission_id) values ('toyota Mark II', 4, 4, 4);





--Вывести список всех машин и все привязанные к ним детали.
select car.name, engine.name, transmission.name, car_body.name from car
  left join car_body  on car.car_body_id = car_body.id
  left join engine on  car.car_engine_id = engine.id
  left join transmission on car.car_transmission_id = transmission.id ;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
-- кузова
select car_body.name from car_body left join car on car.car_body_id = car_body.id where car.car_body_id is null;

-- двигатели
select engine.name from engine left join car on car.car_engine_id = engine.id where car.car_body_id is null;

--кпп
select transmission.name from transmission left join car on car.car_transmission_id = transmission.id where car.car_body_id is null;