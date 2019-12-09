drop table demo_data if exists;


create table demo_data (
    id bigint auto_increment,
    name varchar(255),
    subject varchar(255),
    primary key (id)
);


insert into demo_data (name, subject) values ('dai', '数学');
insert into demo_data (name, subject) values ('zhang', '语文');
insert into demo_data (name, subject) values ('li', '英语');
insert into demo_data (name, subject) values ('wang', '物理');