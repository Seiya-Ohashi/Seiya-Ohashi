create table if not exists user_info (
  user_id int(10) primary key AUTO_INCREMENT,
  user_name varchar(255) not null,
  mail_address varchar(255) not null,
  password varchar(255) not null,
  role varchar(50) default "ROLE_GENERAL" not null,
  CONSTRAINT uq_user_name UNIQUE (user_name),
  CONSTRAINT uq_mail_address UNIQUE (mail_address)
);

create table if not exists store_user_info (
  store_user_id int(10) primary key AUTO_INCREMENT,
  store_mail_address varchar(255) not null,
  password varchar(255) not null,
  role varchar(50) default "ROLE_STORE" not null,
  entry_flag int(1) default 0 not null,
  store_id int(10),
  CONSTRAINT uq_store_mail_address UNIQUE (store_mail_address)
);

create table if not exists store_info (
  store_id int(10) primary key AUTO_INCREMENT,
  store_user_id	int(10) not null,
  store_name varchar(255) not null,
  store_phone_number int(16) not null,
  address1 varchar(255) not null,
  address2 varchar(255) not null,
  address3 varchar(255) not null,
  business_hours varchar(255),
  regular_holiday varchar(255),
  webpage varchar(255),
  CONSTRAINT uq_store_name UNIQUE (store_name),
  CONSTRAINT uq_address3 UNIQUE (address3)
);

ALTER TABLE store_user_info ADD FOREIGN KEY(store_id)
REFERENCES store_info(store_id);
ALTER TABLE store_info ADD FOREIGN KEY(store_user_id)
REFERENCES store_user_info(store_user_id);
