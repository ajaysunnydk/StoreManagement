create database store;
use store;
create table regdetails (uname varchar(200) NOT NULL, paasword varchar(200) NOT NULL , role varchar(200) default "user");
create table items (itemId int(20) primary key NOT NULL, itemName varchar(200) , itemQty int(20) );
insert into regdetails values ("host","host@123","admin");
select * from regdetails;
