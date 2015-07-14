
use easyim;

drop table if exists tb_user;
drop table if exists tb_friendship;
drop table if exists tb_message;
drop table if exists tb_offline_message;

drop index if exists idx_uid_status_on_offline_message;

create table tb_user
(
  id varchar(40) primary key,
  username varchar(50) not null,
  password varchar(256) not null,
  nickname varchar(50),
  avatar varchar (200),
  description varchar(500),
  location varchar(500),
  sex varchar(10),
  device_id varchar(40),
  create_time datetime
);


create table tb_friendship
(
  id varchar(40) primary key ,
  user_id varchar(40) not null,
  friend_id varchar(40) not null
);

create table tb_message
(
  id varchar(40) primary key ,
  from_id varchar(40) not null,
  to_id varchar(40) not null,
  type varchar(40) not null,
  content varchar(2000) not null,
  create_time datetime not null
);

create table tb_offline_message
(
  id varchar(40) primary key,
  user_id varchar(40) not null,
  message_id varchar(40) not null,
  status varchar(20) not null,
  create_time datetime not null
);

create index idx_uid_status_on_offline_message on tb_offline_message(user_id, status);
