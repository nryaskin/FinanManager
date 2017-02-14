create table categories(
category_id serial primary key,
name varchar(100)
);

create table items(
item_id serial primary key,
category_id int,
expenses int,
Date date,
constraint fk_category_id foreign key (category_id) references  categories(category_id)
);



