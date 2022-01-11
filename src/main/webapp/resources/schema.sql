drop table if exists board;

create table board (
	bno bigint not null auto_increment,
	title varchar(200) not null,
	content text not null,
	writer varchar(100) not null,
	reg_at datetime default (now()),
	mod_at datetime default (now()),
	read_count int(10) default 0,
	primary key (bno)
);

create table b_comment (
	cno bigint auto_increment,
	bno bigint not null,
	writer varchar(100) not null,
	content text not null,
	reg_at datetime default now(),
	mod_at datetime default now(),
	primary key (cno)
);


drop table if exists b_file;

create table b_file(
	uuid varchar(256) primary key,
	save_dir varchar(256) not null,
	file_name varchar(512) not null,
	file_type tinyint(1) default 0,
	bno bigint(11),
	file_size bigint,
	reg_at datetime default (now())
);

create table file(
	uuid varchar(256) primary key,
	save_dir varchar(256) not null,
	file_name varchar(512) not null,
	file_type tinyint(1) default 0,
	pno bigint(11),
	file_size bigint,
	reg_at datetime default (now())
);