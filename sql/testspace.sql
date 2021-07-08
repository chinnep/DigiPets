
use cyber_pet;

select * from user;

select * from user inner join user_item inner join item on user.username = user_item.username and user_item.item_id = item.item_id;

select * from user inner join pet on user.username = pet.username where user.username = 'test101';
select * from pet where pet.username = 'test2';

select * from pet;

select * from item;

select * from user;
select * from user_item;

update user set gold = 1000 where username = 'qLir';

select ui.username, ui.item_id, ui.quantity, i.item_name, i.description, i.price, i.for_battle, i.for_food, i.for_water,
	i.for_care, i.for_health, i.img_url from user_item ui inner join item i on ui.item_id = i.item_id where ui.username = 'test';