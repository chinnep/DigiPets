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
    for_battle boolean
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

create table pet_move(
	move_id int not null,
    pet_id int not null,
    constraint fk_pet_move_pet_id
		foreign key (pet_id)
        references pet(pet_id),
	constraint fk_pet_move_move_id
		foreign key (move_id)
        references move(move_id)
);

-- populate data