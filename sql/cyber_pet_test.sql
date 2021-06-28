drop database if exists cyber_pet_test;
create database cyber_pet_test;
use cyber_pet_test;

-- create tables
create table user(
	user_id int primary key,
	gold int
);

create table item(
	item_id int primary key auto_increment,
	item_name varchar(150) not null,
	description varchar(250),
	price float not null,
    for_battle boolean
);

create table user_item(
	quantity int not null,
    user_id int not null,
    item_id int not null,
    constraint pk_user_item
        primary key (user_id, item_id),
	constraint fk_user_item_user_id
        foreign key (user_id)
        references user(user_id),
	constraint fk_user_item_item_id
        foreign key (item_id)
        references item(item_id)
);

create table pet_type(
	pet_type_id int primary key auto_increment,
    pet_type_name varchar(150) not null,
    apetite int not null,
    care int not null,
    thirst int not null,
    health int not null,
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
    time_to_zero time not null,
    is_dead boolean not null,
    trophies int not null,
    pet_type_id int not null,
    user_id int not null,
	constraint fk_pet_pet_type_id
        foreign key (pet_type_id)
        references pet_type(pet_type_id),
	constraint fk_pet_user_id
        foreign key (user_id)
        references user(user_id)
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

drop procedure if exists set_known_good_state;
-- change default delimiter
delimiter //
create procedure set_known_good_state()
begin

	delete from item;
    alter table item auto_increment = 1;
    delete from user;
    alter table user auto_increment = 1;
    delete from user_item;
	delete from pet;
	alter table pet auto_increment = 1;
    delete from pet_move;
    delete from move;
    alter table move auto_increment = 1;
    delete from pet_type;
    alter table pet_type auto_increment = 1;
    
    insert into item(item_name, description, for_battle, price) values
        ('Sledgehammer','A sledgehammer is a tool with a large, flat, often metal head, attached to a long handle. 
        The long handle combined with a heavy head allows the sledgehammer to gather momentum during a swing and apply
        a large force compared to hammers designed to drive nails.', true, 200),
        ('Biscuit','A flour-based baked food product. Give your pet this buttery treat for more <3 points', false, 20);
    
    insert into user(user_id, gold) values
        (1, 1000),
        (2, 2000),
        (3, 3000);
        
	insert into pet_type (pet_type_name, apetite, care, thirst, health, next_pet_type_id)
		values
	('egg', 0,0,0,10,2),
    ('child',10,10,10,10,3),
    ('teen', 50,50,50,50,4),
	('adult',100,100,100,100,null);
        
	insert into move(move_name, damage) 
	values
		('Nuckle Sandwich', 15),
        ('wet willy',20);
        
	insert into pet 
    (pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl, time_to_zero, is_dead, trophies, pet_type_id, user_id)
	values
	('Rick Sanchez',0.9,0.99,0.8,1,'30:00:00.0',false,0,2,1),
	('Morty Smith',1,1,1,1,'30:00:00.0',false,10,1,1),
    ('Summer Smith',1,0.75,1,1,'30:00:00.0',false,1000,4,2);
    
    insert into user_item(user_id, item_id)
    values
    (1,1,10),
    (1,2,5),
    (2,1,3),
    (2,2,19);
            
	insert into pet_move(move_id, pet_id) 
    values
    (1,1),
    (1,2),
    (1,3),
    (2,1),
    (2,2),
    (2,3);
    

end //
-- 4. Change the statement terminator back to the original.
delimiter ;

use cyber_pet_test;

select * from pet;



