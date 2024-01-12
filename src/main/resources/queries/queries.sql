drop database hotel;
show databases;
create database  hotel;
use hotel;

show tables ;

create table customer(
     customer_id  int auto_increment primary key ,
     customer_firstName varchar(100),
     customer_lastName varchar(100),
     customer_address  text,
     customer_nic varchar(100),
     customer_phone  varchar(100),
     customer_email  varchar(100)
);

create table employee(
                         id  int auto_increment primary key ,
                         firstName varchar(100),
                         lastName varchar(100),
                         address  text,
                         nic varchar(100),
                         phone  varchar(100),
                         email  varchar(100)
);

create table payment (
     payment_id  int auto_increment primary key ,
     payment_amount double (10,2),
     payment_method  enum('Credit Card','Cash','others'),
     payment_time time,
     payment_date date
);

create table  pool(
    pool_id  int auto_increment primary key ,
    pool_name  varchar(100),
    pool_description text,
    pool_opening_hours time,
    capacity int ,
    price double(10,2),
    is_available enum('yes','no')

);

create table rooms(
     room_id int auto_increment primary key ,
     room_number int ,
     room_price  double(10,2),
     num_of_guest int,
     bed_count int ,
     has_pool boolean,
     has_ac boolean,
     has_tv boolean,
     has_wifi boolean,
     non_smoking boolean,
     hot_water boolean,
     avaliable boolean,
     id int,
     foreign key (id ) references rooms_category ( id ) on delete cascade on update cascade


);

create table rooms_category(
    id int auto_increment primary key ,
    name varchar(255),
    description text

);
create table user(
    id int primary key auto_increment,
    userName varchar(255) not null ,
    password varchar(255) not null

);

drop table user;

create table hall(
     hall_id  int auto_increment primary key ,
     hall_name  varchar(100),
     hall_capacity  int ,
     hall_price  double(10,2),
     is_available enum('yes','no')

);

drop database hotel;

create table reservation(
     reservation_id  int auto_increment primary key ,
     check_in_date date,
     check_out_date date,
     reservation_status enum('Pending', 'Cancelled'),
     check_in_status enum('Checked In', 'Not Checked In'),
     check_out_status enum('Checked Out', 'Not Checked Out'),
     special_requests text,
     cancellation_reason text,
     customer_id INT NOT NULL ,
     Foreign key ( customer_id ) references customer ( customer_id ) ON UPDATE CASCADE ON DELETE CASCADE,
     payment_id INT NOT NULL ,
     Foreign key ( payment_id ) references payment ( payment_id ) ON UPDATE CASCADE ON DELETE CASCADE

);


create table rooms_details(
     reservation_id INT NOT NULL ,
     Foreign key ( reservation_id ) references reservation ( reservation_id ) ON UPDATE CASCADE ON DELETE CASCADE,
     room_id INT NOT NULL ,
     Foreign key (room_id) references rooms (room_id) ON UPDATE CASCADE ON DELETE CASCADE
);
create table pools_details(
        reservation_id INT NOT NULL ,
         Foreign key ( reservation_id ) references reservation ( reservation_id ) ON UPDATE CASCADE ON DELETE CASCADE,
        pool_id INT NOT NULL ,
        Foreign key ( pool_id ) references pool ( pool_id ) ON UPDATE CASCADE ON DELETE CASCADE
);
create table halls_details(
         reservation_id INT NOT NULL ,
         Foreign key ( reservation_id ) references pools_details ( reservation_id ) ON UPDATE CASCADE ON DELETE CASCADE,
         hall_id INT NOT NULL ,
         Foreign key ( hall_id ) references hall ( hall_id ) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO reservation values (1,'2023-11-12','2023-11-15','Pending','Checked In','Checked Out','hhd','cancel',1,0);
INSERT INTO reservation(reservation_id, check_in_date, check_out_date, reservation_status, check_in_status, check_out_status, special_requests, cancellation_reason, customer_id, payment_id) values (2,'2023-11-12','2023-11-15','Pending','Checked In','Checked Out','hhd','cansel',20,2);
INSERT INTO reservation(reservation_id, check_in_date, check_out_date, reservation_status, check_in_status, check_out_status, special_requests, cancellation_reason, customer_id, payment_id) values (3,'2023-11-12','2023-11-15','Pending','Checked In','Checked Out','hhd','cancel',15,3);


INSERT INTO payment  values (0,200,'Cash',04.15,'2024-04-12');
INSERT INTO payment values (2,300,'others',09.00,'2023-06-4');
INSERT INTO payment values (3,400,'Credit Card',08.45,'2027-06-05');
INSERT INTO payment values (4,400,'Credit Card',08.45,'2027-06-05');

INSERT INTO user values (1,'a','a');

drop table rooms;

show tables ;

select * from customer;

drop database hotel;


