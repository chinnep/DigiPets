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
    pet_type varchar(150) not null,
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
    time_to_zero datetime not null,
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

create procedure set_known_good_state()
begin

	delete from item;
    alter table item auto_increment = 1;
    delete from user;
    alter table user auto_increment = 1;
    delete from user_item;
	delete from agency;
	alter table agency auto_increment = 1;
    delete from agent;
    alter table agent auto_increment = 1;
    delete from security_clearance;
    alter table security_clearance auto_increment = 1;
    
    -- new code: security_clearance is required below...
    insert into security_clearance values
        (1, 'Secret'),
        (2, 'Top Secret');
    
    insert into agency(agency_id, short_name, long_name) values
        (1, 'ACME', 'Agency to Classify & Monitor Evildoers'),
        (2, 'MASK', 'Mobile Armored Strike Kommand'),
        (3, 'ODIN', 'Organization of Democratic Intelligence Networks');
        
	insert into location (location_id, name, address, city, region, country_code, postal_code, agency_id)
		values
	(1, 'HQ', '123 Elm', 'Des Moines', 'IA', 'USA', '55555', 1),
    (2, 'Safe House #1', 'A One Ave.', 'Walla Walla', 'WA', 'USA', '54321-1234', 1),
    (3, 'HQ', '123 Elm', 'Test', 'WI', 'USA', '55555', 2),
	(4, 'Remote', '999 Nine St.', 'Test', 'WI', 'USA', '55555', 2),
	(5, 'HQ', '123 Elm', 'Test', 'WI', 'USA', '55555', 3), -- for delete tests
	(6, 'Remote', '999 Nine St.', 'Test', 'WI', 'USA', '55555', 3);
        
	insert into agent 
		(first_name, middle_name, last_name, dob, height_in_inches) 
	values
		('Hazel','C','Sauven','1954-09-16',76),
		('Claudian','C','O''Lynn','1956-11-09',41),
		('Winn','V','Puckrin','1999-10-21',60),
		('Kiab','U','Whitham','1960-07-29',52),
		('Min','E','Dearle','1967-04-18',44),
		('Urban','H','Carwithen',null,58),
		('Ulises','B','Muhammad','2008-04-01',80),
		('Phylys','Y','Howitt','1979-03-28',68);
        
	insert into alias (`name`, persona, agent_id)
			values
            ('Rick Sanchez','disagreeable genius scientist',1),
            ('Morty Smith','scared preteen',2),
            ('Summer Smith','classic teenager',3);
            
	insert into agency_agent 
		(agency_id, agent_id, identifier, security_clearance_id, activation_date)
    select
        agency.agency_id,                              -- agency_id
        agent.agent_id,                                -- agent_id
        concat(agency.agency_id, '-', agent.agent_id), -- identifier
        1,                     					       -- security_clearance_id
        date_add(agent.dob, interval 10 year)          -- activation_date
    from agency
    inner join agent
    where agent.agent_id not in (6, 8)
    and agency.agency_id != 2;

end //
-- 4. Change the statement terminator back to the original.
delimiter ;