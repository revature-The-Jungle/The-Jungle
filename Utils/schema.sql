-- Table for user
create table jungle_user (
	user_id serial primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	username varchar(50) not null unique,
	password varchar(50) not null,
	user_about varchar(200) default 'Pending',
	user_birth_date varchar(10) not null,
	user_image_data varchar(300),
	status varchar (100) default 'Pending',
	email varchar(100) default 'Pending',
);

-- Properties for posts, user groups, user followers, user events, user see first
-- These will need to be added to the user table.