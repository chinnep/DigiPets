drop database if exists cyber_pet;
create database cyber_pet;
use cyber_pet;

-- create tables
create table user(
	username varchar(150) primary key,
    password_hash varchar(250) not null,
	gold int
);

create table item(
	item_id int primary key auto_increment,
	item_name varchar(150) not null,
	description varchar(250),
	price int not null,
    for_battle boolean,
    for_food boolean,
    for_water boolean,
    for_care boolean,
    for_health boolean,
    img_url varchar(50)
);

create table user_item(
	quantity int not null,
    username varchar(250) not null,
    item_id int not null,
    constraint pk_user_item
        primary key (username, item_id),
	constraint fk_user_item_username
        foreign key (username)
        references user(username),
	constraint fk_user_item_item_id
        foreign key (item_id)
        references item(item_id)
);

create table pet_type(
	pet_type_id int primary key auto_increment,
    pet_type_name varchar(150) not null,
    -- points/hr that it decreases
    appetite float not null,
    care float not null,
    thirst float not null,
    -- starting total for battle
    health float not null,
    next_pet_type_id int,
    constraint fk_next_pet_type_id
        foreign key (next_pet_type_id)
        references pet_type(pet_type_id)
);

create table pet(
	pet_id int primary key auto_increment,
    pet_name varchar(100)not null,
    hunger_lvl float not null,
    care_lvl float not null,
    thirst_lvl float not null,
    health_lvl float not null,
    time_at_last_login varchar(30),
    is_dead boolean not null,
    trophies int not null,
    pet_type_id int not null,
    username varchar(250) not null,
	constraint fk_pet_pet_type_id
        foreign key (pet_type_id)
        references pet_type(pet_type_id),
	constraint fk_pet_username
        foreign key (username)
        references user(username)
);

create table move(
	move_id int primary key auto_increment,
    move_name varchar(150) not null,
    damage int not null
);

create table pet_type_move(
	move_id int not null,
    pet_type_id int not null,
    constraint pk_move_pet_type
        primary key (move_id, pet_type_id),
    constraint fk_pet_type_move_pet_id
		foreign key (pet_type_id)
        references pet_type(pet_type_id),
	constraint fk_pet_type_move_move_id
		foreign key (move_id)
        references move(move_id)
);

-- populate data

-- items
insert into item(item_name, description, for_battle, for_food, for_water, for_care, for_health, price, img_url) values
	('Super Melon', 'The Super Melon smells and looks amazing!! It could probably feed and water your pet for days!', false, true, true, false, false, 350, '/melon.jpg'),
    ('Meaty Vegetable', 'It looks kind of weird... but all the pets seem to be salivating over it.', false, true, false, false, false, 200, '/meaty veg.jpg'),
    ('Magical Fountain', "This fountain seems to create water out of nowhere! Don't leave it inside unless you want a flooded basement.", false, false, true, false, false, 200, '/fountain.jpg'),
    ('Precious Caterpillar', 'Uh oh... Your pet already seems attached to this cute plush caterpillar.', false, false, false, true, false, 200, '/caterpillar.jpg'),
	('Sledgehammer','A sledgehammer is a tool with a large, flat, often metal head, attached to a long handle.', true, false, false, false, false, 22000, '/sledgehammer.png');

-- petType
insert into pet_type(pet_type_name, appetite, care, thirst, health, next_pet_type_id)
		values
	('young',2.0,2.0,1.0,30,null),
	('baby',2.0,3.0,1.0,20,1),
	('egg',0,3.0,0,10,2),
    ('rhino',2.0,0.5,2,10,null),
    ('alien',0.2,1,0,10,null),
    ('dead',0,0,0,0,null);
    
insert into move(move_name, damage) values
	('Crack',0),
	('Roll',1),
	('Gum',2),
	('Goo',2),
	('Roll',3),
    ('Skewer',10),
    ('Stomp',10),
    ('Laser',10),
    ('Spinning Charge', 10);
        
insert into pet_type_move(pet_type_id, move_id) values
	(1,1), (1,2),
	(2,3), (2,4),
	(3,4), (3,5),
    (4,6), (4,7),
    (5,8), (5,9);
    
-- Prototype user: username: Ash or Nemesis, password: password
insert into user(username, password_hash, gold) values
	('Ash','$2a$10$WjNHV53U7CuBQyAm9Dxs1u.XDwB7Rrs2ZaytPZfO8.mIuYHjEBCAy',10000),
    ('Nemesis','$2a$10$WjNHV53U7CuBQyAm9Dxs1u.XDwB7Rrs2ZaytPZfO8.mIuYHjEBCAy',10000);
        
insert into user_item(quantity, username, item_id) values
	(1,'Ash',1),
    (1,'Ash',2),
    (1,'Ash',3),
    (1,'Ash',4),
    (1,'Ash',5),
    (1,'Nemesis',1),
    (1,'Nemesis',2),
    (1,'Nemesis',3),
    (1,'Nemesis',4),
    (1,'Nemesis',5);
    
insert into pet (pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl,time_at_last_login,is_dead,trophies,pet_type_id,username) values
	('Eggy',50,73,100,100,'2021-07-07T10:15:30',false,1500,3,'Ash'),
    ('Baby',30,25,44,100,'2021-07-07T10:15:30',false,1500,2,'Ash'),
    ('Youngin',100,100,100,100,'2021-07-07T10:15:30',false,1500,1,'Ash'),
    ('Bob',0,0,0,0,'2021-07-07T10:15:30',true,1320,6,'Ash'),
    ('ET',100,100,100,100,'2021-07-07T10:15:30',false,1772,5,'Ash'),
    ('Stomper',100,100,100,100,'2021-07-07T10:15:30',false,1821,4,'Nemesis');