create table users(
username VARCHAR(100) PRIMARY KEY,
password VARCHAR(100)
);

create table category(
id_category varchar(255) PRIMARY KEY
);

create table currency(
current_id SERIAL  PRIMARY KEY, 
type VARCHAR(100) UNIQUE
);

create table account(
id VARCHAR(40) PRIMARY KEY,
username VARCHAR(100),
balance NUMERIC(9,2),
current_id INTEGER,
constraint fk_current_id foreign key (current_id) references currency(current_id) on update cascade on delete cascade,
constraint fk_username foreign key (username) references users(username) on update cascade on delete cascade
);

create table transaction(
id  SERIAL PRIMARY KEY,
source VARCHAR(40),
target VARCHAR(40),
category_id VARCHAR(255),
cash NUMERIC(9,2),
current_id INTEGER,
state VARCHAR(40),
constraint fk_current_id foreign key (current_id) references currency(current_id) on update cascade on delete cascade,
constraint fk_source foreign key (source) references account(id) on update cascade on delete cascade,
constraint fk_target foreign key (target) references account(id) on update cascade on delete cascade,
constraint fk_category_id foreign key (category_id) references category(id_category) on update cascade on delete cascade
);

INSERT INTO currency(type) VALUES('EURO');
INSERT INTO currency(type) VALUES('DOLLAR');
INSERT INTO currency(type) VALUES('RUBLE');
