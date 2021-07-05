drop database if exists cyber_pet_test;
create database cyber_pet_test;
use cyber_pet_test;

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
    for_health boolean
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

drop procedure if exists set_known_good_state;
-- change default delimiter
delimiter //
create procedure set_known_good_state()
begin

	SET SQL_SAFE_UPDATES = 0;

	SET FOREIGN_KEY_CHECKS = 0;
    
    delete from pet_type;
    alter table pet_type auto_increment = 1;
    
    SET FOREIGN_KEY_CHECKS = 1;

	delete from user_item;
    delete from pet_type_move;
    delete from pet;
	alter table pet auto_increment = 1;
	delete from item;
    alter table item auto_increment = 1;
    delete from user;
    alter table user auto_increment = 1;
    delete from move;
    alter table move auto_increment = 1;
    
    insert into item(item_name, description, for_battle, for_food, for_water, for_care, for_health, price) values
        ('Sledgehammer','A sledgehammer is a tool with a large, flat, often metal head, attached to a long handle.', true, false, false, false, false, 200),
        ('Biscuit','A flour-based baked food product. Give your pet this buttery treat for more <3 points', false, true, false, false, false, 20);
    
    insert into user(username, password_hash, gold) values
        ('abc123','$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa',1000),
        ('tamagotchi-gang','$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQb',2000),
        ('dev10peeps','$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQc',3000);
        
	insert into move(move_name, damage) values
		('Knuckle Sandwich',15),
        ('Wet willy',20);
        
	insert into pet_type(pet_type_name, appetite, care, thirst, health, next_pet_type_id)
		values
	('adult',100,100,100,100,null),
    ('teen', 50,50,50,50,1),
    ('child',10,10,10,10,2),
	('egg', 0,0,0,10,3);
    
    insert into pet_type_move(move_id, pet_type_id) values
    (1,1),
    (1,2),
    (1,3),
    (2,1),
    (2,2);
        
	insert into pet 
    (pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl, time_at_last_login, is_dead, trophies, pet_type_id, username)
	values
		('Rick Sanchez',100,100,100,100,'2011-12-03T10:15:30',false,0,2,'abc123'),
		('Morty Smith',50,50,50,50,'2011-12-03T10:15:30',false,10,1,'tamagotchi-gang'),
		('Summer Smith',1,0.75,1,1,'1998-01-01T23:59:59',false,1000,4,'dev10peeps');
    
    insert into user_item(username, item_id, quantity)
    values
    ('abc123',1,10),
    ('abc123',2,5),
    ('tamagotchi-gang',1,3),
    ('tamagotchi-gang',2,19);
    
    SET SQL_SAFE_UPDATES = 1;

end //
-- 4. Change the statement terminator back to the original.
delimiter ;

call set_known_good_state();

select * from pet_type_move;